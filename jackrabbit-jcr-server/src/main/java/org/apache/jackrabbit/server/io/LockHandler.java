/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jackrabbit.server.io;

import org.apache.jackrabbit.webdav.DavResource;
import org.apache.jackrabbit.webdav.lock.LockManager;

/**
 * The LockHandler is invoked when a webdav LOCK/UNLOCK request is received. Implementers of this interface should plugin
 * their LOCK related checks here and ensure that an appropriate LockManager is returned only if the checks succeed.
 */
public interface LockHandler {

    /**
     * Validates if this handler can execute a lock operation on the given resource.
     *
     * @param lockContext The context of the lock
     * @param resource The resource to be locked
     * @return {@code true} if this instance is permitted to execute lock operation on the given resource;
     *         {@code false} otherwise.
     */
    public boolean canLock(LockContext lockContext, DavResource resource);

    /**
     * Returns a LockManager. Implementers must take care that a lockmanager is returned only if this handler is
     * permitted to lock the resource.
     *
     * @param resource which is to be locked
     * @return lockmanger for performing lock related webdav operations on specified resource
     */
    public LockManager getLockManager(DavResource resource);

}
