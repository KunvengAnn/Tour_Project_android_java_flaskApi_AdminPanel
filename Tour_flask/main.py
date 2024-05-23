from flask import Flask
from flask_restful import Api
from flask_cors import CORS  # Import CORS from flask_cors

from PackageTour import PackageTour
from Customer import Customer
from Booking import Booking
from Refund import Refund
from Payment import Payment
from Staff import Staff
from ReceiveText import ReceiveText
from Email import Email
from PaymentCustomerPKTour import CustomerPaymentPKTour

app = Flask(__name__)
api = Api(app)

# Add CORS to your app
CORS(app)

api.add_resource(PackageTour, '/api/tours', '/api/tours/<int:tour_id>')
api.add_resource(Customer, '/api/customer', '/api/customer/<int:customer_id>')
api.add_resource(Booking, '/api/booking', '/api/booking/<int:booking_id>')
api.add_resource(Refund, '/api/refund', '/api/refund/<int:refund_id>')
api.add_resource(Payment, '/api/payment', '/api/payment/<int:payment_id>')
api.add_resource(Staff, '/api/staff', '/api/staff/<int:staff_id>')

# api.add_resource(ReceiveText, '/api/receive-text')
api.add_resource(CustomerPaymentPKTour, '/api/payment-customer-pktour')

api.add_resource(Email, '/api/email-sender')

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
