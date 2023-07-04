/*
  Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.

  Licensed under the Apache License, Version 2.0 (the "License").
  You may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
package software.amazon.msk.auth.iam.internals;

import software.amazon.msk.auth.iam.internals.IAMSaslClient.IAMSaslClientFactory;

import java.security.Provider;
import java.security.Security;

import static software.amazon.msk.auth.iam.internals.IAMSaslClient.getMechanismNameForClassLoader;

public class IAMSaslClientProvider extends Provider {
    /**
     * Constructs a IAM Sasl Client provider with a fixed name, version number,
     * and information.
     */
    protected IAMSaslClientProvider() {
        super("SASL/IAM Client Provider (" +
                IAMSaslClientProvider.class.getClassLoader().hashCode(), 1.0,
                ") SASL/IAM Client Provider for Kafka");
        put("SaslClientFactory." + getMechanismNameForClassLoader(getClass().getClassLoader()), IAMSaslClientFactory.class.getName());
    }

    public static void initialize() {
        Security.addProvider(new IAMSaslClientProvider());
    }
}
