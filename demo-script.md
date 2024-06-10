# ADC demo commands
gcloud auth list

# My identity already has Vertex AI User role
gcloud auth application-default login

# Deploy to Cloud Run
gcloud run deploy --service-account vertex-ai-caller@persian-java-talk.iam.gserviceaccount.com