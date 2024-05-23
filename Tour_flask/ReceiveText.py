from flask import Flask, jsonify, request
from flask_restful import Api, Resource
import json

text = None

class ReceiveText(Resource):
    def get(self):
        try:
            response = {'text': text}
            return json.dumps(response), 200, {'Content-Type': 'application/json'}
        except Exception as e:
            response = {'error': str(e)}
            return json.dumps(response), 500, {'Content-Type': 'application/json'}

    def post(self):
        try:
            global text  # Use the global variable
            data = request.get_json()
            text = data['text']  # Update the global variable
            response = {'text': text}
            return json.dumps(response), 200, {'Content-Type': 'application/json'}
        except Exception as e:
            response = {'error': str(e)}
            return json.dumps(response), 500, {'Content-Type': 'application/json'}