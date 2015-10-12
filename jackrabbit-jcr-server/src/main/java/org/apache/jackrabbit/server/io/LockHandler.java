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
import org.apache.jackrabbit.webdav.lock.LockManager;

/**
 * The LockHandler is invoked when a webdav LOCK/UNLOCK request is received. Implementers of this interface should plugin
 * their handling of LOCK/UNLOCK request here
 */
public interface LockHandler {

    /**
     * Validates if this handler is able to execute a lock operation with the given
     * parameters.
     *
     * @param lockContext The context of the lock
     * @param resource The resource to be locked
     * @return {@code true} if this instance can successfully executed the lock operation with the given parameters;
     *         {@code false} otherwise.
     */
    public boolean canLock(LockContext lockContext, DavResource resource);

    /**
     * Validates if this handler is able to execute a unlock operation with the given
     * parameters.
     *
     * @param lockContext The context of the lock
     * @param resource The resource to be unlocked
     * @return {@code true} if this instance can successfully executed the unlock operation with the given parameters;
     *         {@code false} otherwise.
     */
    public boolean canUnlock(LockContext lockContext, DavResource resource);

    public LockManager getLockManager();

}
