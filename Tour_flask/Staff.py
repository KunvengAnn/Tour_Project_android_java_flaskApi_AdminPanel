from flask import request
from flask_restful import Resource
from connection import connect_to_sql_server

class Staff(Resource):
    def get(self, staff_id=None):
        if staff_id is None:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Staff @action='getAll_Staff'")
                
                staffs = cursor.fetchall()
                conn.close()
                
                return {'staffs': staffs}, 200
            except Exception as e:
                return {'error': str(e)}, 500
        else:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Staff @action='getStaffByID', @staff_id=" + str(staff_id))
                staff = cursor.fetchone()
                conn.close()

                if not staff:
                    return {'message': 'Staff not found'}, 404

                return {'staff': staff}, 200
            except Exception as e:
                return {'error': str(e)}, 500
            
    def post(self):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Staff @action='addStaff', "\
                    "@payment_id=%s, "\
                    "@staff_name=%s, "\
                    "@staff_phone=%s, "\
                    "@staff_address=%s"

            params = (
                data['payment_id'],
                data['staff_name'],
                data['staff_phone'],
                data['staff_address']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Staff added successfully'}, 201
        except Exception as e:
            return {'error': str(e)}, 500
        
    def put(self, staff_id):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Staff @action='updateStaff', "\
                    "@staff_id=%s, "\
                    "@payment_id=%s, "\
                    "@staff_name=%s, "\
                    "@staff_phone=%s, "\
                    "@staff_address=%s"

            params = (
                staff_id,
                data['payment_id'],
                data['staff_name'],
                data['staff_phone'],
                data['staff_address']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Staff updated successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500

    def delete(self, staff_id):
        try:
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Staff @action='deleteStaff', "\
                    "@staff_id=%s"

            cursor.execute(query, (staff_id,))

            conn.commit()
            conn.close()

            return {'message': 'Staff deleted successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500
