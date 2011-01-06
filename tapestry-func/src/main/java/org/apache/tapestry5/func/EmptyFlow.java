// Copyright 2010 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.apache.tapestry5.func;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * An implementation of {@link Flow} for empty flows. This allows for some easy optimizations.
 * 
 * @since 5.2.0
 */
class EmptyFlow<T> extends AbstractFlow<T>
{
    public T first()
    {
        return null;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public Flow<T> rest()
    {
        return this;
    }

    /** Does nothing; returns this empty list. */
    public Flow<T> each(Worker<? super T> worker)
    {
        return this;
    }

    /** Does nothing; returns this empty list. */
    public Flow<T> filter(Predicate<? super T> predicate)
    {
        return this;
    }

    /** Does nothing; returns this empty list. */
    public Flow<T> remove(Predicate<? super T> predicate)
    {
        return this;
    }

    /** Does nothing; returns this empty list (as a Flow<X>). */
    public <X> Flow<X> map(Mapper<T, X> mapper)
    {
        return F.emptyFlow();
    }

    /** Does nothing; returns the initial value. */
    public <A> A reduce(Reducer<A, T> reducer, A initial)
    {
        return initial;
    }

    /** Does nothing; returns this empty list. */
    public Flow<T> reverse()
    {
        return this;
    }

    /** Does nothing; returns this empty list. */
    public Flow<T> sort()
    {
        return this;
    }

    /** Does nothing; returns this empty list. */
    public Flow<T> sort(Comparator<T> comparator)
    {
        return this;
    }

    /** Returns the empty list. */
    public List<T> toList()
    {
        return Collections.emptyList();
    }

    /** Returns the other list (i.e. empty ++ other == other). */
    @SuppressWarnings("unchecked")
    public Flow<T> concat(Flow<? extends T> other)
    {
        return (Flow<T>) other;
    }

    public <X> Flow<X> mapcat(Mapper<T, Flow<X>> mapper)
    {
        return F.emptyFlow();
    }

    public int count()
    {
        return 0;
    }

    public Flow<T> take(int length)
    {
        return this;
    }

    public Flow<T> drop(int length)
    {
        return this;
    }

    public Set<T> toSet()
    {
        return Collections.emptySet();
    }

    @Override
    public Flow<T> removeNulls()
    {
        return this;
    }
}
