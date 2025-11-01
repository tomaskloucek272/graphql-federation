import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { IntrospectAndCompose } from '@apollo/gateway';
import { ApolloGatewayDriver, ApolloGatewayDriverConfig } from '@nestjs/apollo';
import { GraphQLModule } from '@nestjs/graphql';

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloGatewayDriverConfig>({
      driver: ApolloGatewayDriver,
      gateway: {
        supergraphSdl: new IntrospectAndCompose({
          subgraphs: [
            { name: 'clients', url: 'http://localhost:8081/graphql' },
            { name: 'orders', url: 'http://localhost:8082/graphql' },
          ],
        }),
        debug: true, 
      },
    }),
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
