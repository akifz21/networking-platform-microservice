import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import TfidfVectorizer
from fastapi.middleware.cors import CORSMiddleware
from fastapi import FastAPI
import requests

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.post("/recommendation/")
async def recommendation(user_description: str):
    jobs_response = requests.get("http://localhost:8040/")
    if jobs_response.status_code == 200:
        jobs = jobs_response.json()
    else:
        return {"error": "Jobs error"}

    job_descriptions = [job["description"] for job in jobs]

    vectorizer = TfidfVectorizer(stop_words='english')
    job_vectors = vectorizer.fit_transform(job_descriptions)

    user_vector = vectorizer.transform([user_description])

    similarities = cosine_similarity(user_vector, job_vectors).flatten()

    recommended_jobs = []
    for i, similarity_score in enumerate(similarities):
        if similarity_score > 0.0:
            recommended_jobs.append(
                {"job": jobs[i], "similarity_score": similarity_score})

    return {"recommended_jobs": recommended_jobs}


@app.get("/")
async def getAll():
    job_response = requests.get("http://localhost:8040/")
    return {"jobs": job_response.json()}
