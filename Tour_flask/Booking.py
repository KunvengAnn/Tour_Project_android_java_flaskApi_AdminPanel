from flask import request
from flask_restful import Resource
from connection import connect_to_sql_server

class Booking(Resource):
    def get(self, booking_id=None):
        if booking_id is None:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)
                cursor.execute("EXEC SP_Booking @action='getAll_Booking'")
                
                bookings = cursor.fetchall()
                conn.close()

                for booking in bookings:
                    booking['booking_date'] = booking['booking_date'].strftime("%Y-%m-%d") 

                return {'bookings': bookings}, 200
            except Exception as e:
                return {'error': str(e)}, 500
        else:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Booking @action='getBookingByID', @booking_id=" + str(booking_id))
                booking = cursor.fetchone()
                conn.close()

                if not booking:
                    return {'message': 'Booking not found'}, 404

                # Format booking_date, Date_of_department, payment_date, and payment_status before returning
                booking['booking_date'] = booking['booking_date'].strftime("%Y-%m-%d")
                booking['Date_of_department'] = booking['Date_of_department'].strftime("%Y-%m-%d")
                booking['payment_date'] = booking['payment_date'].strftime("%Y-%m-%d")
                booking['payment_status'] = str(booking['payment_status'])

                return {'booking': booking}, 200
            except Exception as e:
                return {'error': str(e)}, 500
        
    def post(self):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Booking @action='addBooking', "\
                    "@tour_id=%s, "\
                    "@customer_id=%s, "\
                    "@booking_date=%s, "\
                    "@number_of_child=%s, "\
                    "@number_of_adult=%s, "\
                    "@total_price=%s"

            params = (
                data['tour_id'],
                data['customer_id'],
                data['booking_date'],
                data['number_of_child'],
                data['number_of_adult'],
                data['total_price']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Booking added successfully'}, 201
        except Exception as e:
            return {'error': str(e)}, 500
        
    def put(self, booking_id):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Booking @action='updateBooking', "\
                    "@booking_id=%s, "\
                    "@tour_id=%s, "\
                    "@customer_id=%s, "\
                    "@booking_date=%s, "\
                    "@number_of_child=%s, "\
                    "@number_of_adult=%s, "\
                    "@total_price=%s"

            params = (
                booking_id,
                data['tour_id'],
                data['customer_id'],
                data['booking_date'],
                data['number_of_child'],
                data['number_of_adult'],
                data['total_price']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Booking updated successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500

    def delete(self, booking_id):
        try:
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Booking @action='deleteBooking', "\
                    "@booking_id=%s"

            cursor.execute(query, (booking_id,))

            conn.commit()
            conn.close()

            return {'message': 'Booking deleted successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500
