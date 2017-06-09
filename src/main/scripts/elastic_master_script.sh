#!/bin/bash
echo 'Elastic Server ::::::: '$1 ' and replica '$2

echo "****************DELETING EXISTING INDEXES****************\n"
curl -X DELETE $1/file
echo "-------------file index deleted-------------\n"

echo "****************CREATING INDEXES****************\n"
curl -X PUT $1/file -d '
index :
    number_of_shards : 8 
    number_of_replicas : '$2' 
'
echo "-------------file index created-------------\n"

echo "****************INSERTING MAPPING****************\n"
curl -X POST $1/file/file/_mapping -d @file_mapping.json
sleep 1