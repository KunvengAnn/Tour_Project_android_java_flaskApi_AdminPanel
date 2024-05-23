from flask import request
from flask_restful import Resource
from connection import connect_to_sql_server

class Refund(Resource):
    def get(self, refund_id=None):
        if refund_id is None:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Refund @action='getAll_Refund'")
                
                refunds = cursor.fetchall()
                conn.close()
                
                return {'refunds': refunds}, 200
            except Exception as e:
                return {'error': str(e)}, 500
        else:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Refund @action='getRefundByID', @refund_id=" + str(refund_id))
                refund = cursor.fetchone()
                conn.close()

                if not refund:
                    return {'message': 'Refund not found'}, 404

                return {'refund': refund}, 200
            except Exception as e:
                return {'error': str(e)}, 500
            
    def post(self):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Refund @action='addRefund', "\
                    "@booking_id=%s, "\
                    "@refund_date=%s, "\
                    "@refund_amount=%s, "\
                    "@refund_reason=%s"

            params = (
                data['booking_id'],
                data['refund_date'],
                data['refund_amount'],
                data['refund_reason']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Refund added successfully'}, 201
        except Exception as e:
            return {'error': str(e)}, 500
        
    def put(self, refund_id):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Refund @action='updateRefund', "\
                    "@refund_id=%s, "\
                    "@booking_id=%s, "\
                    "@refund_date=%s, "\
                    "@refund_amount=%s, "\
                    "@refund_reason=%s"

            params = (
                refund_id,
                data['booking_id'],
                data['refund_date'],
                data['refund_amount'],
                data['refund_reason']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Refund updated successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500

    def delete(self, refund_id):
        try:
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Refund @action='deleteRefund', "\
                    "@refund_id=%s"

            cursor.execute(query, (refund_id,))

            conn.commit()
            conn.close()

            return {'message': 'Refund deleted successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500
