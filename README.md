aws kinesis create-stream --stream-name some-data-stream --shard-count 1 --endpoint-url http://localhost:4566
aws kinesis put-record --stream-name some-data-stream --partition-key 123 --cli-binary-format raw-in-base64-out --endpoint-url http://localhost:4566 --data sampledata
