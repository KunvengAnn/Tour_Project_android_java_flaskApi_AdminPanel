from flask import request
from flask_restful import Resource
from connection import connect_to_sql_server

class Customer(Resource):
    def get(self, customer_id=None):
        if customer_id is None:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Customer @action='getAll_Customer'")
                
                customers = cursor.fetchall()
                conn.close()

                # Convert date objects to strings
                for customer in customers:
                    if 'Date_of_department' in customer:
                        customer['Date_of_department'] = customer['Date_of_department'].strftime('%Y-%m-%d')

                return {'customers': customers}, 200
            except Exception as e:
                return {'error': str(e)}, 500
        else:
            try:
                conn = connect_to_sql_server()
                cursor = conn.cursor(as_dict=True)

                cursor.execute("EXEC SP_Customer @action='getCustomerByID', @customer_id=" + str(customer_id))
                customer = cursor.fetchone()
                conn.close()

                if not customer:
                    return {'message': 'Customer not found'}, 404

                # Convert date object to string
                if 'Date_of_department' in customer:
                    customer['Date_of_department'] = customer['Date_of_department'].strftime('%Y-%m-%d')

                return {'customer': customer}, 200
            except Exception as e:
                return {'error': str(e)}, 500
    def post(self):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Customer @action='addCustomer', "\
                    "@title=%s, "\
                    "@first_name=%s, "\
                    "@last_name=%s, "\
                    "@customer_phone=%s, "\
                    "@customer_email=%s, "\
                    "@customer_from_country=%s, "\
                    "@Date_of_department=%s"

            params = (
                data['title'],
                data['first_name'],
                data['last_name'],
                data['customer_phone'],
                data['customer_email'],
                data['customer_from_country'],
                data['Date_of_department']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Customer added successfully'}, 201
        except Exception as e:
            return {'error': str(e)}, 500
        
    def put(self, customer_id):
        try:
            data = request.json
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Customer @action='updateCustomer', "\
                    "@customer_id=%s, "\
                    "@title=%s, "\
                    "@first_name=%s, "\
                    "@last_name=%s, "\
                    "@customer_phone=%s, "\
                    "@customer_email=%s, "\
                    "@customer_from_country=%s, "\
                    "@Date_of_department=%s"

            params = (
                customer_id,
                data['title'],
                data['first_name'],
                data['last_name'],
                data['customer_phone'],
                data['customer_email'],
                data['customer_from_country'],
                data['Date_of_department']
            )

            cursor.execute(query, params)

            conn.commit()
            conn.close()

            return {'message': 'Customer updated successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500

    def delete(self, customer_id):
        try:
            conn = connect_to_sql_server()
            cursor = conn.cursor()

            query = "EXEC SP_Customer @action='deleteCustomerID', "\
                    "@customer_id=%s"

            cursor.execute(query, (customer_id,))

            conn.commit()
            conn.close()

            return {'message': 'Customer deleted successfully'}, 200
        except Exception as e:
            return {'error': str(e)}, 500
