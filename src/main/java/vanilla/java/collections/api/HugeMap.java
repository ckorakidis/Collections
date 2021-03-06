package vanilla.java.collections.api;

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

import java.util.Map;

public interface HugeMap<K, V> extends Map<K, V>, HugeContainer {
    @Override
    public HugeSet<K> keySet();

    @Override
    public HugeCollection<V> values();

    @Override
    public HugeSet<Entry<K, V>> entrySet();

    public HugeCollection<HugeEntry<K, V>> filter(Predicate<HugeEntry<K, V>> predicate);

    public <T> HugeCollection<T> forEach(Procedure<HugeEntry<K, V>, T> procedure);

    public int update(Updater<HugeEntry<K, V>> updater);

    public Class<K> keyType();

    public Class<V> valueType();
}
