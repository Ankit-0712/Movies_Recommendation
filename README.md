# Movies Recommendation Application 🎬

Movies Recommendation Application is an Android-based project built using Java in Android Studio for the frontend and Python (Flask) for the backend.  
This application recommends movies to users based on a dataset sourced from Kaggle and uses machine learning techniques in the backend to generate recommendations.

---

## Features 🚀
- Movie recommendation system based on user input
- Android Mobile Application built with Java
- Python Flask backend for API and ML logic
- Kaggle dataset used for training
- Easy to run locally

---

## Tech Stack 🛠️
- Android Studio (Java) — Frontend
- Python (Backend)
- Flask (Python Web Framework)
- Machine Learning (Recommendation System)
- Dataset from Kaggle

---

## Dataset 📊
Dataset used in this project is downloaded from Kaggle.  
Make sure to download the dataset from Kaggle and place it in the backend folder.

---
## How to Run This Project 💻

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/movies-recommendation-application.git
cd movies-recommendation-application
cd backend
pip install -r requirements.txt
python app.py
By default, the Flask server will start at:
http://127.0.0.1:5000/

Running Android Frontend (Java)
Open android-app folder in Android Studio.

Configure API URL in your Java code (usually in Retrofit or API Client file) to point to:

perl
Copy
Edit
http://your-local-ip:5000/
Run the Android Application on Emulator or Physical Device.

Note: Both Android app and Flask server should run on the same network for proper API communication.

