/*
 * Copyright 2016-2018 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github4s.unit

import cats.Id
import github4s.GithubResponses.{GHResponse, GHResult}
import github4s.HttpClient
import github4s.api.Organizations
import github4s.free.domain.User
import github4s.utils.BaseSpec

class OrganizationsSpec extends BaseSpec {

  "Organization.listMembers" should "call to httpClient.get with the right parameters" in {

    val response: GHResponse[List[User]] =
      Right(GHResult(List(user), okStatusCode, Map.empty))

    val httpClientMock = httpClientMockGet[List[User]](
      url = s"orgs/$validRepoOwner/members",
      response = response
    )

    val organizations = new Organizations[String, Id] {
      override val httpClient: HttpClient[String, Id] = httpClientMock
    }
    organizations.listMembers(sampleToken, headerUserAgent, validRepoOwner)
  }

  "Organization.listOutsideCollaborators" should "call to httpClient.get with the right parameters" in {

    val response: GHResponse[List[User]] =
      Right(GHResult(List(user), okStatusCode, Map.empty))

    val httpClientMock = httpClientMockGet[List[User]](
      url = s"orgs/$validOrganizationName/outside_collaborators",
      response = response
    )

    val organizations = new Organizations[String, Id] {
      override val httpClient: HttpClient[String, Id] = httpClientMock
    }
    organizations.listOutsideCollaborators(sampleToken, headerUserAgent, validOrganizationName)
  }

}
