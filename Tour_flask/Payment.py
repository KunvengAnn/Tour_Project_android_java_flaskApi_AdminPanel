from flask import request
from flask_restful import Resource
from connection import connect_to_sql_server
import datetime

class Payment(Resource):
    def get(self, payment_id=None):
        if payment_id is None:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Payment @action='getAll_Payment'")
                payments = cursor.fetchall()
                conn.close()
                
                # Convert the payments to JSON-serializable format
                for payment in payments:
                    if isinstance(payment['payment_date'], datetime.date):
                        payment['payment_date'] = payment['payment_date'].isoformat()
                    if isinstance(payment['payment_status'], bool):
                        payment['payment_status'] = str(payment['payment_status']).lower()

                return {'payments': payments}, 200
            except Exception as e:
                return {'error': str(e)}, 500
        else:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Payment @action='getPaymentByID', @payment_id=" + str(payment_id))
                payment = cursor.fetchone()
                conn.close()

                if not payment:
                    return {'message': 'Payment not found'}, 404

                # Convert the payment to JSON-serializable format
                if isinstance(payment['payment_date'], datetime.date):
                    payment['payment_date'] = payment['payment_date'].isoformat()
                if isinstance(payment['payment_status'], bool):
                    payment['payment_status'] = str(payment['payment_status']).lower()

                return {'payment': payment}, 200
            except Exception as e:
                return {'error': str(e)}, 500 
    
    def post(self):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Payment @action='addPayment', "\
                    "@booking_id=%s, "\
                    "@payment_date=%s, "\
                    "@amount_paid=%s, "\
                    "@payment_status=%s"

            params = (
                data['booking_id'],
                data['payment_date'],
                data['amount_paid'],
                data['payment_status']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Payment added successfully'}, 201
        except Exception as e:
            return {'error': str(e)}, 500
        
    def put(self, payment_id):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            if 'payment_status' in data:
                # If 'payment_status' is present, execute 'updateOnlyOneField'
                query = "EXEC SP_Payment @action='updateOnlyOneField', "\
                        "@payment_id=%s, "\
                        "@payment_status=%s"

                params = (
                    payment_id,
                    data['payment_status']
                )
            else:
                query = "EXEC SP_Payment @action='updatePayment', "\
                        "@payment_id=%s, "\
                        "@booking_id=%s, "\
                        "@payment_date=%s, "\
                        "@amount_paid=%s, "\
                        "@payment_status=%s"

                params = (
                    payment_id,
                    data['booking_id'],
                    data['payment_date'],
                    data['amount_paid'],
                    data['payment_status']
                )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Payment updated successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500

    def delete(self, payment_id):
        try:
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Payment @action='deletePayment', "\
                    "@payment_id=%s"

            cursor.execute(query, (payment_id,))

            conn.commit()
            conn.close()

            return {'message': 'Payment deleted successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500
