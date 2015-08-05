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

import java.util.ArrayList;
import java.util.List;

public class LockOperationManagerImpl implements LockOperationManager {

    private static LockOperationManager DEFAULT_MANAGER;

    private final List<LockHandler> lockHandlers = new ArrayList<LockHandler>();

    /**
     * @see LockOperationManager#lock(org.apache.jackrabbit.server.io.LockContext, org.apache.jackrabbit.webdav.DavResource)
     */
    public boolean lock(LockContext lockContext, DavResource member) throws DavException {
        boolean success = false;
        LockHandler[] lockHandlers = getLockHandlers();
        for (int i = 0; i < lockHandlers.length && !success; i++) {
            LockHandler dh = lockHandlers[i];
            if (dh.canLock(lockContext, member)) {
                success = dh.lock(lockContext, member);
            }
        }
        return success;
    }

    /**
     * @see LockOperationManager#unlock(org.apache.jackrabbit.server.io.LockContext, org.apache.jackrabbit.webdav.DavResource)
     */
    public boolean unlock(LockContext lockContext, DavResource member) throws DavException {
        boolean success = false;
        LockHandler[] lockHandlers = getLockHandlers();
        for (int i = 0; i < lockHandlers.length && !success; i++) {
            LockHandler dh = lockHandlers[i];
            if (dh.canUnlock(lockContext, member)) {
                success = dh.unlock(lockContext, member);
            }
        }
        return success;
    }

    /**
     * @see LockOperationManager#addLockHandler(org.apache.jackrabbit.server.io.LockHandler)
     */
    public void addLockHandler(LockHandler lockHandler) {
        if (lockHandler == null) {
            throw new IllegalArgumentException("'null' is not a valid LockHandler.");
        }
        lockHandlers.add(lockHandler);

    }

    /**
     * @see LockOperationManager#getLockHandlers()
     */
    public LockHandler[] getLockHandlers() {
        return lockHandlers.toArray(new LockHandler[lockHandlers.size()]);
    }

    /**
     * Returns this lock manager singleton
     */
    public static LockOperationManager getDefaultManager() {
        if (DEFAULT_MANAGER == null) {
            LockOperationManager manager = new LockOperationManagerImpl();
            manager.addLockHandler(new DefaultHandler());
            DEFAULT_MANAGER = manager;
        }
        return DEFAULT_MANAGER;
    }

}
