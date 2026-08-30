from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class BlogRequest(BaseModel):
    title: str
    content: str

@app.post("/ai/classify")
def classify(request: BlogRequest):
    print("제목:", request.title)
    print("본문:", request.content)

    return {
        "category": "TECH",
        "confidence": 0.99
    }