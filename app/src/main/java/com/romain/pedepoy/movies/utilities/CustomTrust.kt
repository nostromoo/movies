/*
 * Copyright (C) 2015 Square, Inc.
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
package com.romain.pedepoy.movies.utilities

import java.io.IOException
import java.security.cert.X509Certificate
import okhttp3.OkHttpClient
import okhttp3.Request.Builder
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.decodeCertificatePem

class CustomTrust {
  // PEM files for root certificates of Comodo and Entrust. These two CAs are sufficient to view
  // https://publicobject.com (Comodo) and https://squareup.com (Entrust). But they aren't
  // sufficient to connect to most HTTPS sites including https://godaddy.com and https://visa.com.
  // Typically developers will need to get a PEM file from their organization's TLS administrator.
  val myCertificateAuthority = """
    -----BEGIN CERTIFICATE-----
MIIDbzCCAlegAwIBAgIUTHilMcrZees/uRY+LuVa+2MegrAwDQYJKoZIhvcNAQEL
BQAwRzELMAkGA1UEBhMCRlIxFzAVBgNVBAgMDlNvbWV3aGVyZSBuaWNlMQ0wCwYD
VQQKDARUUkFYMRAwDgYDVQQDDAdUcmF4IENBMB4XDTE5MDYxMzIzMzY1OFoXDTI5
MDYxMDIzMzY1OFowRzELMAkGA1UEBhMCRlIxFzAVBgNVBAgMDlNvbWV3aGVyZSBu
aWNlMQ0wCwYDVQQKDARUUkFYMRAwDgYDVQQDDAdUcmF4IENBMIIBIjANBgkqhkiG
9w0BAQEFAAOCAQ8AMIIBCgKCAQEAookji0vL8/+ok3J/pj59ckGYSHxDWzlDCaP4
Qo18f4TINzbqmIguOZxneicEKV7A+goPJJZSVsFwF58SckmHX4bK1yDoio/bUnSl
TD89M9GmvXs7EaTPSwW9vo21Dn31yrM1ZvFSNoca+RNCJj0/AODYN96TCZSYBWIv
o54XEUuxxVzaguHfqLuCFGlUxgVgzVaQICJXWaXRf+sSW3xtp2S7/Uo6DAaRDngJ
4h+bgvg357fQqIA291k1WbabCmpobrpWMmWA6lHh1Wss7yUfeoO26yr8qu6/tEpu
xbCnIoUHDO1C6y87v9nP4A29PCf69vK+lX+Ck94+T7udNCSkawIDAQABo1MwUTAd
BgNVHQ4EFgQUuhC6ZP0sImTHV1l5jtPQ6JbPpkUwHwYDVR0jBBgwFoAUuhC6ZP0s
ImTHV1l5jtPQ6JbPpkUwDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQsFAAOC
AQEAKtpOcMF+nXzWm1r9GXxLGfLw04oHtFnHgXPjv/62LRxYacI/z4dVJ0sDBDjl
ZWZx5UqrAObsWdPOBygE6JHp2RaOe/Ai/34FkKj7UYu75teuEasfnwW/AyPgiYlc
yHEmIcI0IjCJKzFlA3HKCG+crc02JggLAnHWenDYKgFsbcHZzRaANPCSkSzeuG90
091rHKpqqjASNtq/6w1B/zecwY8DcNs7X94FTqDKuKIwykByz7aADB4N2Gbd6EAK
l8RKV8JdbDdBZ++REng6YMrwvAkKkqMEnLy+5pcxeXQDHC0pciz+/+0DlBdCjT/Z
WUBYiZg0IgbbV8SqxOaQYK6lhw==
-----END CERTIFICATE-----
    """.trimIndent().decodeCertificatePem()


  private val client: OkHttpClient

  init {
    // This implementation just embeds the PEM files in Java strings; most applications will
    // instead read this from a resource file that gets bundled with the application.
    val certificates =
      HandshakeCertificates.Builder()
          .addTrustedCertificate(myCertificateAuthority)
          // Uncomment if standard certificates are also required.
          // .addPlatformTrustedCertificates()
          .build()
    client = OkHttpClient.Builder()
        .sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager)
        .build()
  }

}

fun main() {
  CustomTrust()
}
