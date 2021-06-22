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

package io.tackle.tcd.windup.service;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.jboss.windup.graph.GraphContext;
import org.jboss.windup.graph.model.WindupFrame;
import org.jboss.windup.graph.model.WindupVertexFrame;
import org.jboss.windup.graph.service.GraphService;

public class GetOrCreateGraphService<T extends WindupVertexFrame> extends GraphService<T> {

    public String TYPE;

    public GetOrCreateGraphService(GraphContext context, Class<T> type) {
        super(context, type);
        try {
            TYPE = (String) type.getField("TYPE").get(null);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public T getOrCreate(String key, String value) {
        GraphTraversal<Vertex, Vertex> pipeline;
        pipeline = new GraphTraversalSource(getGraphContext().getGraph()).V();
        pipeline.has(WindupFrame.TYPE_PROP, TYPE);
        pipeline.has(key, value);

        if (pipeline.hasNext()) {
            T result = frame(pipeline.next());
            return result;
        } else {
            T model = create();
            model.setProperty(key, value);
            return model;
        }
    }
}
