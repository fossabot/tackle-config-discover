/*
Copyright IBM Corporation 2021
Licensed under the Eclipse Public License 2.0, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package io.tackle.tcd.windup.model;

import java.util.List;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.jboss.windup.graph.Adjacency;
import org.jboss.windup.graph.model.TypeValue;
import org.jboss.windup.graph.model.WindupVertexFrame;

@TypeValue(PropsModel.TYPE)
public interface PropsModel extends WindupVertexFrame {

    String TYPE = "PropsModel";
    String PROFILES = "profiles";

    @Adjacency(label = PROFILES, direction = Direction.OUT)
    List<ProfileModel> getProfiles();

    @Adjacency(label = PROFILES, direction = Direction.OUT)
    void addProfile(ProfileModel profile);
}