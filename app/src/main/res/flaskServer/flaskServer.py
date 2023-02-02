
import os
import urllib.request
from flask import Flask, request
from werkzeug.utils import secure_filename
import itertools

UPLOAD_FOLDER = './saved-videos/'

app = Flask(__name__)

@app.route("/")
def showHomePage():
    return "Successful Connection AK"

@app.route("/debug", methods=["POST"])
def debug():
    text = request.form["sample"]
    print(text)
    return "received"

@app.route("/video", methods=["POST"])
def video():
    print("made it into /video http post endpoint ")
    try:
        file = request.files['uploaded_file']
        filename = file.filename
        print("filename: " + filename)
        filename = secure_filename(file.filename)
        #fn = "".join(itertools.takewhile(str.isalpha, filename))
        #print("fn: " + fn)
        file.save(os.path.join(UPLOAD_FOLDER, filename))
    except Exception as e:
        print("Exception ak: " + str(e))

    return "receieved ak"


if __name__ == "__main__":
  app.run(host="0.0.0.0")