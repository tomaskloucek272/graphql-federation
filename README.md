<h1>NestJS Apollo Federation Gateway for Spring Boot subgraphs</h1>

<img width="512" height="840" alt="image" src="https://github.com/user-attachments/assets/5d4afde7-7ad3-4275-a93e-bdac558e015e" />

<h3>Client microservice GraphQL schema: </h3>

<pre><code>
type Client @key(fields: "id") {
    id: ID!
    name: String!
    email: String
    createdAt: String
    updatedAt: String
}

type Query {
    client(id: ID!): Client
}
</code></pre>

<h3>Orders microservice GraphQL schema: </h3>

<pre><code>
type Order @key(fields: "id") {
    id: ID!
    clientId: ID!
    total: Float!
    status: String!
    createdAt: String
}

type Client @key(fields: "id") @extends {
    id: ID @external
    orders: [Order]
}

type Query {
    order(id: ID!): Order
}
</code></pre>

<h3>Merged federated entity of Client done automatically by NestJS gateway</h3>

After starting both Spring boot microservices run NestJS gateway and check <b>http://localhost:3000</b></br>
Gateway should merge both GraphQL graphs <b>where Client is owned by client microservice and orders are owned by orders microservice</b>:

<pre><code>
type Client {
   createdAt: String
   email: String
   id: ID
   name: String!
   updatedAt: String
   orders: [Order]
}
</code></pre>

Now query the gateway:

<pre><code>
curl -s http://localhost:3000/graphql \
  -H 'Content-Type: application/json' \
  --data-binary '{"query":"{ client(id:\"1\") { name email orders { total } } }"}' | jq
</code></pre>

results:

<img width="1458" height="757" alt="image" src="https://github.com/user-attachments/assets/bd6e88c9-6ac4-44ce-9a5b-b38678e4d3da" />

Now how federation worked between both microservices:

- Gateway first contacted client microservice for entity with ID = 1. (client console => 'Invoking getClient')
- Gateway then contacted orders microservice with _entities query. (orders console => 'OrderDatafetcherImpl: client')
- Gateway in the final steps contacts again orders for the final hydration (orders console => 'OrderDatafetcherImpl: resolveOrders')

=> so we have two microservices both contributing with data for <b>ONE federated type = Client</b>







