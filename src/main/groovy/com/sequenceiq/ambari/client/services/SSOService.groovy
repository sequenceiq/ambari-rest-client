/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sequenceiq.ambari.client.services

import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j
import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseException
import org.apache.http.client.ClientProtocolException

@Slf4j
trait SSOService extends CommonService {

  /**
   * Configure SSO for ambari.
   *
   * @param ssoProperties
   */
  def void configureSSO(Map ssoProperties) throws URISyntaxException, ClientProtocolException, HttpResponseException, IOException {
    def Map<String, ?> putRequestMap = [:]
    putRequestMap.put('requestContentType', ContentType.URLENC)
    putRequestMap.put('path', "services/AMBARI/components/AMBARI_SERVER/configurations/sso-configuration")

    Map bodyMap = [
            'Configuration': [category: 'sso-configuration', properties: ssoProperties]
    ]

    putRequestMap.put('body', new JsonBuilder(bodyMap).toPrettyString())
    utils.putAndGetId(putRequestMap)
  }

}