package vanilla.java.collections;

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

import vanilla.java.collections.api.HugeList;
import vanilla.java.collections.api.impl.ByteBufferAllocator;
import vanilla.java.collections.impl.DirectByteBufferAllocator;
import vanilla.java.collections.impl.GenerateHugeArrays;
import vanilla.java.collections.impl.HugeCollectionBuilder;
import vanilla.java.collections.impl.MemoryMappedByteBufferAllocator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HugeArrayBuilder<T> extends HugeCollectionBuilder<T> {
    private Class<?> arrayListClass;


    protected HugeArrayBuilder() {
        super(0);
    }


    public HugeList<T> create() {
        normaliseArgs();

        try {
            if (arrayListClass == null)
                arrayListClass = classLoader().loadClass(typeModel.baseType().getName() + "ArrayList");

        } catch (ClassNotFoundException e) {
            acquireImplClass();
            defineClass(GenerateHugeArrays.dumpPointer(typeModel));
            defineClass(GenerateHugeArrays.dumpPartition(typeModel));
            arrayListClass = defineClass(GenerateHugeArrays.dumpArrayList(typeModel));
        }
        try {
            int partitionSize = this.partitionSize;
            Class<T> type = typeModel.baseType();
            ByteBufferAllocator bba = baseDirectory == null ? new DirectByteBufferAllocator() : new MemoryMappedByteBufferAllocator(new File(baseDirectory));

            return (HugeList<T>) arrayListClass
                    .getConstructor(int.class, Class.class, ByteBufferAllocator.class)
                    .newInstance(partitionSize, type, bba);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e.getCause());
        }
    }

    public T createBean() {
        Class implClass = acquireImplClass();
        try {
            return (T) implClass.newInstance();
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    Class implClass = null;

    private Class acquireImplClass() {
        try {
            if (implClass == null)
                implClass = classLoader().loadClass(typeModel.baseType().getName() + "Impl");

        } catch (ClassNotFoundException e) {
            implClass = defineClass(GenerateHugeArrays.dumpImpl(typeModel));
        }
        return implClass;
    }

    private Class defineClass(byte[] bytes) {
        try {
            Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class /*name*/, byte[].class /*b*/, int.class /*off*/, int.class /*len*/);
            defineClass.setAccessible(true);
            return (Class) defineClass.invoke(classLoader, null, bytes, 0, bytes.length);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e.getCause());
        }
    }
}
