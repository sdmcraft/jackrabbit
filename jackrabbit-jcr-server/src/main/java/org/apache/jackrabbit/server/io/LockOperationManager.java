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

import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.DavResource;

/**
 * The LockManager handles LOCK operation by delegating it to its handlers. It also provides a way
 * to register {@link LockHandler} within it. Implementers of this interface
 * must invoke the registered lock handlers appropriately when a LOCK/UNLOCK operation is to be performed
 */
public interface LockOperationManager {

    /**
     * Delegates the lock operation to the fist handler that accepts it.
     *
     * @param lockContext The context associated with the LOCK operation
     * @param resource The resource to be locked
     * @return {@code true} if this instance successfully executed the lock operation with the given parameters;
     *         {@code false} otherwise.
     * @throws org.apache.jackrabbit.webdav.DavException If an error occurs.
     */
    public boolean lock(LockContext lockContext, DavResource resource) throws DavException;

    /**
     * Delegates the unlock operation to the fist handler that accepts it.
     *
     * @param lockContext The context associated with the LOCK operation
     * @param resource The resource to be unlocked
     * @return {@code true} if this instance successfully executed the unlock operation with the given parameters;
     *         {@code false} otherwise.
     * @throws org.apache.jackrabbit.webdav.DavException If an error occurs.
     */
    public boolean unlock(LockContext lockContext, DavResource resource) throws DavException;

    /**
     * Registers a lock handler
     *
     * @param lockHandler Registers a lock handler with this lock manager
     */
    public void addLockHandler(LockHandler lockHandler);

    /**
     * Returns the registered lock handlers
     *
     * @return An array of all the registered lock handlers.
     */
    public LockHandler[] getLockHandlers();
}
