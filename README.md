# **Link Converter**
It is an link converter application to convert deeplink to web url and vice versa.

### **Technologies**
- Java 8
- Spring Boot Framework
- PostgreSql
- Docker
- Junit
- Mockito
- Swagger UI

### **Features**

For API documentation, please see  at http://localhost:8080/swagger-ui.html

There are three page types:

**Product Page**

- Deep link identifier is that page parameter has "**Product**" value.
- Web url identifier is that there is a "**-p-**" prefix in the path.

**Search Page**

- Deep link identifier is that page parameter has "**Search**" value.
- Web url identifier is that there is a "**/sr**" prefix in the path.

**Other Page**

- If there is no match with the other identifiers, it is accepted as a other page.

### **Run Application**

Please run the docker commands below:
```
1. cd {project-path}
2. docker build -t link-converter .
3. docker-compose up
```
After executing the commands, link converter application and postgresql DB get ready to use.

### **Sample Payloads**
To convert web url to deep link:

Path: /linkConverter/deepLink

Request:
```json 

{
    "link": "https://www.trendyol.com/sr?q=ceket"
}
```
Response:
```
ty://?Page=Search&Query=ceket
```

To convert deep link to web url :

Path: /linkConverter/webUrl

Request:
```json 

{
    "link" : "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
}
```
Response:
```
https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064
```

To see the saved requests, please execute the commands below:
```
1. docker exec -tiu postgres postgres-db psql
2. select * from "url_map";
```

