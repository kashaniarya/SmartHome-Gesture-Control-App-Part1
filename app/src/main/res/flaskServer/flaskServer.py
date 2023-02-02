
import os
import urllib.request
from flask import Flask, request
from werkzeug.utils import secure_filename
import itertools

UPLOAD_FOLDER = './saved-videos/'

gestureDict = {
                  "LightOn": 0,
                  "LightOff": 0,
                  "FanOn": 0,
                  "FanOff": 0,
                  "FanUp": 0,
                  "FanDown": 0,
                  "SetThermo": 0,
                  "Num0": 0,
                  "Num1": 0,
                  "Num2": 0,
                  "Num3": 0,
                  "Num4": 0,
                  "Num5": 0,
                  "Num6": 0,
                  "Num7": 0,
                  "Num8": 0,
                  "Num9": 0
              }


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
    try:
        file = request.files['uploaded_file']
        filename = file.filename
        print("filename: " + filename)
        key = "".join(itertools.takewhile(str.isalpha, filename))
        gestureDict[key] += 1
        value = str(gestureDict[key])
        fn = key + "_PRACTICE_" + value
        fn = fn + ".mp4"
        fn = secure_filename(fn)
        file.save(os.path.join(UPLOAD_FOLDER, fn))
    except Exception as e:
        print("Exception ak: " + str(e))
    return "receieved ak"


if __name__ == "__main__":
  app.run(host="0.0.0.0")