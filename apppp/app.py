from flask import Flask, request, jsonify

import pickle
import numpy as np
import pandas as pd

model = pickle.load(open('similarity.pkl',"rb"))
new_df =  pickle.load(open('movie_dict.pkl',"rb"))

if isinstance(new_df, dict):
    new_df = pd.DataFrame(new_df)

def recommend(movie: str):
    try:

        movie = movie.strip().lower()
        new_df['normalized_title'] = new_df['title'].str.strip().str.lower()


        matching_movies = new_df[new_df['normalized_title'] == movie]
        if matching_movies.empty:
            return jsonify({"error": f"'{movie}' not found in the dataset!"}), 404


        movie_index = matching_movies.index[0]
        cluster = model[movie_index]


        movies_list = sorted(list(enumerate(cluster)), reverse=True, key=lambda x: x[1])[1:10]
        recommended_movies = [new_df.iloc[i[0]]['title'] for i in movies_list]

        return jsonify(recommended_movies)
    except Exception as e:
        return jsonify({"error": f"An unexpected error occurred: {str(e)}"}), 500


app = Flask(__name__)



@app.route('/')
def home():
    return "Hello World"


@app.route('/predict', methods=['GET'])
def predict():
    movie_name = request.args.get('movie_name')
    if not movie_name:
        return jsonify({"error": "Please provide a movie name"}), 400
    return recommend(movie_name)

if __name__ == '__main__':
    app.run(debug=True)
