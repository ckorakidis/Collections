package vanilla.java.collections.model;

/*
 * Copyright 2011 Peter Lawrey
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import vanilla.java.collections.api.impl.BCType;
import vanilla.java.collections.impl.MappedFileChannel;

import java.io.*;
import java.nio.CharBuffer;
import java.util.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Enumerated16FieldModel<T> extends AbstractFieldModel<T> {
    private static final String NULL_STRING = "\0\0";
    private final Map<T, Character> map = new LinkedHashMap<T, Character>();
    private final List<T> list = new ArrayList<T>();
    private final Class<T> type;
    private int addPosition;

    public Enumerated16FieldModel(String fieldName, Class<T> type) {
        super(fieldName);
        this.type = type;
        clear();
    }

    @Override
    public Class storeType() {
        return CharBuffer.class;
    }

    @Override
    public Class type() {
        return type;
    }

    @Override
    public Object arrayOfField(int size) {
        return newArrayOfField(size, null);
    }

    @Override
    public int sizeOf(int elements) {
        return sizeOf0(elements);
    }

    private static int sizeOf0(int elements) {
        return elements * 2;
    }

    public static CharBuffer newArrayOfField(int size, MappedFileChannel mfc) {
        return acquireByteBuffer(mfc, sizeOf0(size)).asCharBuffer();
    }

    public void set(CharBuffer array, int index, T value) {
        Character ordinal = map.get(value);
        if (ordinal == null) {
            final int size = map.size();
            if (size >= Character.MAX_VALUE)
                throw new IllegalStateException("Cannot enumerate more than " + (int) Character.MAX_VALUE + " values in a partition.");
            OUTER:
            do {
                for (; addPosition < map.size(); addPosition++) {
                    if (list.get(addPosition) == null) {
                        ordinal = addEnumValue(value, addPosition);
                        break OUTER;
                    }
                }
                ordinal = addEnumValue(value, size);
            } while (false);
            addPosition++;
        }
        array.put(index, ordinal);
    }

    public T get(CharBuffer buffer, int offset) {
        char ch = buffer.get(offset);
        try {
            return list.get(ch);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Object id " + (int) ch + " is not valid, must be less than " + list.size(), e);
        }
    }

    private Character addEnumValue(T value, int position) {
        char ordinal = (char) position;
        if (ordinal != position)
            throw new IndexOutOfBoundsException("Too many values in Enumerated16 field, try calling compact()");
        map.put(value, ordinal);
        if (ordinal == list.size())
            list.add(ordinal, value);
        else
            list.set(ordinal, value);
        return ordinal;
    }

    @Override
    public BCType bcType() {
        return BCType.Reference;
    }

    @Override
    public String bcLSetType() {
        return "Ljava/lang/Object;";
    }

    @Override
    public String bcLStoredType() {
        return "C";
    }

    @Override
    public boolean virtualGetSet() {
        return true;
    }

    @Override
    public boolean isCallsNotEquals() {
        return true;
    }

    @UsedFromByteCode
    public static <T> boolean notEquals(T t1, T t2) {
        return t1 == null ? t2 != null : !t1.equals(t2);
    }

    @UsedFromByteCode
    public static <T> int hashCode(T elementType) {
        return elementType == null ? Integer.MIN_VALUE : elementType.hashCode();
    }

    public void clear() {
        map.clear();
        list.clear();
        map.put(null, (char) 0);
        list.add(null);
        addPosition = 1;
    }

    private final BitSet compactIndexUsed = new BitSet();


    public void compactStart() {
        compactIndexUsed.clear();
    }

    public void compactScan(CharBuffer charBuffer, long size) {
        for (int i = 0; i < size; i++) {
            final char ch = charBuffer.get(i);
            compactIndexUsed.set(ch);
        }
    }

    public void compactEnd() {
        final int compactSize = compactIndexUsed.cardinality();
        if (compactSize == map.size()) {
            return;
        }
        for (int i = 1; i < list.size(); i++) {
            if (compactIndexUsed.get(i)) continue;
            // to be removed
            T t = list.get(i);
            list.set(i, null);
            map.remove(t);
            if (addPosition > i)
                addPosition = i;
        }
    }

    public Map<T, Character> map() {
        return map;
    }

    public List<T> list() {
        return list;
    }

    @Override
    public boolean isCompacting() {
        return true;
    }

    @Override
    public int equalsPreference() {
        return 15;
    }

    public void load(File dir, int partitionNumber) {
        if (dir == null) {
            clear();
            return;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new InflaterInputStream(new BufferedInputStream(new FileInputStream(fileFor(dir, partitionNumber)))));
            list.clear();
            list.addAll((Collection<T>) ois.readObject());
            ois.close();
            map.clear();
            for (int i = 0; i < list.size(); i++)
                map.put(list.get(i), (char) i);

        } catch (FileNotFoundException ignoed) {
            clear();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private File fileFor(File dir, int partitionNumber) {
        return new File(dir, fieldName() + "-model-" + partitionNumber);
    }

    public void save(File dir, int partitionNumber) {
        try {
            final File file = fileFor(dir, partitionNumber);
            if (list.size() <= 1) {
                file.delete();
                return;
            }
            ObjectOutputStream oos = new ObjectOutputStream(
                    new DeflaterOutputStream(new BufferedOutputStream(new FileOutputStream(file + ".tmp"))));
            oos.writeObject(list);
            oos.close();
            if (!file.exists() || file.delete())
                if (!new File(file + ".tmp").renameTo(file))
                    throw new IllegalStateException("Unable to rename " + file + ".tmp");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
