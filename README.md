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

After starting both Spring boot microservices run NestJS gateway and check <b>http://localhost:3000</b>

<code><pre>
type Client {
  createdAt: String
  email: String
  id: ID
  name: String!
  updatedAt: String
  orders: [Order]
}
</code></pre>







