/*
 * Copyright (c) 2011 Peter Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vanilla.java.collections.impl;

import vanilla.java.collections.api.HugeList;
import vanilla.java.collections.api.HugeListIterator;

public class SubList<E> extends AbstractHugeCollection<E> implements HugeList<E> {
    private final AbstractHugeCollection<E> list;
    private long start;
    private long end;

    public SubList(AbstractHugeCollection<E> list, long start, long end) {
        super(list.elementType(), null);
        size = new SubListSizeHolder();
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean add(E e) {
        list.add(end, e);
        end++;
        return true;
    }

    @Override
    protected void growCapacity(long capacity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void recycle(Object recycleable) {
        list.recycle(recycleable);
    }

    @Override
    public void recycle() {
        list.subListPoolAdd(this);
    }

    @Override
    public HugeList<E> subList(long fromIndex, long toIndex) {
        return list.subList(start + fromIndex, start + toIndex);
    }

    @Override
    public HugeListIterator<E> listIterator(long start, long end) {
        return list.listIterator(this.start + start, this.start + end);
    }

    @Override
    public E get(long index) {
        return list.get(start + index);
    }

    @Override
    public E remove(long index) {
        return list.remove(start + index);
    }

    @Override
    public E set(long index, E element) {
        return list.set(start + index, element);
    }

    @Override
    public int partitionSize() {
        return list.partitionSize();
    }

    class SubListSizeHolder extends SimpleSizeHolder {
        @Override
        public int partitionSize() {
            return SubList.this.partitionSize();
        }

        @Override
        public long size() {
            return SubList.this.size();
        }
    }
}
