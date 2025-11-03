<h1>NestJS GraphQL gateway for Spring Boot GraphQL microservices</h1>

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

