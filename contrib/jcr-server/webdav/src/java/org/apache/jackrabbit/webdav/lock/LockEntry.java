/*
 * Copyright 2005 The Apache Software Foundation.
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
package org.apache.jackrabbit.webdav.lock;

import org.jdom.Element;

/**
 * <code>LockEntry</code>...
 */
public interface LockEntry {

    /**
     * Returns the type of this lock entry
     *
     * @return type of this lock entry.
     */
    public Type getType();

    /**
     * Returns the scope of this lock entry.
     *
     * @return scope of this lock entry.
     */
    public Scope getScope();

    /**
     * Returns the Xml representation of this entry.
     *
     * @return Xml representation
     */
    public Element toXml();
}