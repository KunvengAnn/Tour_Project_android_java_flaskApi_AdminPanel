from flask import request
from flask_restful import Resource
from connection import connect_to_sql_server

class PackageTour(Resource):
    def get(self, tour_id=None):
        if tour_id is None:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_PackageTour @action='getAll_Tour'")
                
                tours = cursor.fetchall()
                conn.close()
                
                return {'tours': tours}, 200
            except Exception as e:
                return {'error': str(e)}, 500
        else:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_PackageTour @action='getTourByID', @tour_id=" + str(tour_id))
                tour = cursor.fetchone()
                conn.close()

                if not tour:
                    return {'message': 'Tour not found'}, 404

                return {'tour': tour}, 200
            except Exception as e:
                return {'error': str(e)}, 500
            
    def post(self):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_PackageTour @action='add_Tour', "\
                    "@tour_name=%s, "\
                    "@tour_description=%s, "\
                    "@tour_duration=%s, "\
                    "@tour_price=%s, "\
                    "@tour_location=%s"

            params = (
                data['tour_name'],
                data['tour_description'],
                data['tour_duration'],
                data['tour_price'],
                data['tour_location']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Tour inserted'}, 201
        except Exception as e:
            return {'error': str(e)}, 500
        
    def put(self, tour_id):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_PackageTour @action='updateTour', "\
                    "@tour_id=%s, "\
                    "@tour_name=%s, "\
                    "@tour_price=%s, "\
                    "@tour_description=%s, "\
                    "@tour_duration=%s, "\
                    "@tour_location=%s"

            params = (
                tour_id,
                data['tour_name'],
                data['tour_price'],
                data['tour_description'],
                data['tour_duration'],
                data['tour_location']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Tour updated'}, 200
        except Exception as e:
            return {'error': str(e)}, 500

    def delete(self, tour_id):
        try:
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            # Execute the stored procedure to delete a tour
            query = "EXEC SP_PackageTour @action='deleteTour', "\
                    "@tour_id=%s"

            cursor.execute(query, (tour_id,))

            conn.commit()
            conn.close()

            return {'message': 'Tour deleted'}, 200
        except Exception as e:
            return {'error': str(e)}, 500