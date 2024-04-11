# Simple Rag Exercise

## Prerequisites

- have a working java environment
- install the [quarkus CLI](https://quarkus.io/guides/cli-tooling)
- Install [ollama](https://ollama.com/download)
- install the llama2 model `ollama run llama2`

# running the project

```
quarkus dev
```

once the project starts you can go to the dev ui to submit a query: 
http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui


Test the different answers with and without the RAG 

Note: unless you have a GPU on your laptop, it's going to be slow. The timeout is set to 10 minutes

with ollama directly:

```sh
curl http://localhost:11434/api/generate -d '{
  "model": "llama2",
  "prompt":"What is the best way to organize an environment as a service experience in a Kubernetes or Openshift environment?"
}'
```

via the RAG service

```sh
curl -X 'GET' \
  'http://localhost:8080/query-raffa?question=What%20is%20the%20best%20way%20to%20organize%20an%20environment%20as%20a%20service%20experience%20in%20a%20Kubernetes%20or%20Openshift%20environment%3F&session=1' \
  -H 'accept: text/plain'
```  

