/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.caching.http.internal

import groovy.transform.SelfType
import org.gradle.integtests.fixtures.AbstractIntegrationSpec
import org.gradle.integtests.fixtures.BuildCacheFixture
import org.gradle.test.fixtures.server.http.HttpBuildCache
import org.junit.Before
import org.junit.Rule

@SelfType(AbstractIntegrationSpec)
trait HttpBuildCacheFixture extends BuildCacheFixture {
    private HttpBuildCache httpBuildCache

    @Rule
    HttpBuildCache getHttpBuildCache() {
        return httpBuildCache
    }

    @Before
    void createBuildCache() {
        httpBuildCache = new HttpBuildCache(temporaryFolder)
    }

    String useHttpBuildCache(URI uri) {
        """
            buildCache {  
                local {
                    enabled = false
                }
                remote(org.gradle.caching.http.HttpBuildCache) {
                    url = "${uri}/"   
                    push = true
                }
            }
        """
    }
}
