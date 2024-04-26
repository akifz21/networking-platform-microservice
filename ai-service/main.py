import requests
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
import spacy

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


nlp = spacy.load("en_core_web_md")


@app.post("/recommendation/")
async def recommendation(user_description: str):
    jobs_response = requests.get("http://localhost:8040/")
    if jobs_response.status_code == 200:
        jobs = jobs_response.json()
    else:
        return {"error": "Jobs error"}

    user_doc = nlp(user_description)
    recommended_jobs = []
    for job_description in jobs:
        job_doc = nlp(job_description["description"])
        similarity_score = user_doc.similarity(job_doc)
        if similarity_score > 0.3:
            recommended_jobs.append(
                {"job": job_description, "similarity_score": similarity_score})
    return {"recommended_jobs": recommended_jobs}


@app.get("/")
async def getAll():
    job_response = requests.get("http://localhost:8040/")
    return {"jobs": job_response.json()}
