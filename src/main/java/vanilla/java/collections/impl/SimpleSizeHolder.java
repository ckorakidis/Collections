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

import vanilla.java.collections.api.impl.SizeHolder;

import java.io.IOException;

public abstract class SimpleSizeHolder implements SizeHolder {
    @Override
    public void size(long size) {
        if (size() != size)
            throw new UnsupportedOperationException();
    }

    @Override
    public void capacity(long capacity) {
        size(capacity);
    }

    @Override
    public long capacity() {
        return size();
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }


    @Override
    public void addToSize(int i) {
        throw new Error("Not implemented");
    }

    @Override
    public void partitionSize(int partitionSize) {
        throw new Error("Not implemented");
    }

    @Override
    public void keyPartitions(int keyPartitions) {
        throw new Error("Not implemented");
    }

    @Override
    public int keyPartitions() {
        throw new Error("Not implemented");
    }
}
