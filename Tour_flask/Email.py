from flask import request
from flask_restful import Resource
from smtplib import SMTP_SSL
from email.message import EmailMessage
import json

# Load credentials from JSON file
with open("credentials.json") as file:
    credentials = json.load(file)

SENDER_EMAIL = credentials["email"]
MAIL_PASSWORD = credentials["password"]

def send_mail(sender_email, receiver_email, mail_password, subject, body):
    msg = EmailMessage()
    msg["From"] = sender_email
    msg["To"] = receiver_email
    msg["Subject"] = subject
    msg.add_alternative(body, subtype="html")

    with SMTP_SSL("smtp.gmail.com", 465) as smtp:
        smtp.login(sender_email, mail_password)
        smtp.send_message(msg)
    print("Mail Sent Successfully")

class Email(Resource):
    def post(self):
        data = request.json

        receiver_email = data.get('receiver_email', 'venganncoco@gmail.com')  # default to 'venganncoco@gmail.com' if not provided

        # Extracting form fields from JSON payload
        bill_id = data.get('bill_id', 'Unknown')
        title = data.get('title', 'Unknown')
        first_name = data.get('first_name', 'Unknown')
        last_name = data.get('last_name', 'Unknown')
        tour_name = data.get('tour_name', 'Unknown')
        num_children = data.get('num_children', 'Unknown')
        num_adults = data.get('num_adults', 'Unknown')
        total_price = data.get('total_price', 'Unknown')
        date_departure = data.get('date_departure', 'Unknown')

        # Email body formatted booking form
        body = f"""
        <html>
        <head>
            <style>
                body {{
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #f4f4f4;
                }}
                .container {{
                    width: 100%;
                    max-width: 600px;
                    margin: 20px auto;
                    padding: 20px;
                    background-color: #ffffff;
                    border-radius: 10px;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                }}
                .header {{
                    text-align: center;
                    padding-bottom: 20px;
                    border-bottom: 1px solid #dddddd;
                }}
                .header h1 {{
                    margin: 0;
                    color: #333333;
                }}
                .details {{
                    margin: 20px 0;
                }}
                .details div {{
                    margin-bottom: 15px;
                    display: flex;
                    justify-content: space-between;
                }}
                .details div span {{
                    font-weight: bold;
                    color: #555555;
                }}
                .details div p {{
                    margin: 0;
                    color: #333333;
                }}
                .footer {{
                    text-align: center;
                    padding-top: 20px;
                    border-top: 1px solid #dddddd;
                    color: #777777;
                }}
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <h1>Tour Booking Confirmation</h1>
                </div>
                 <h2>Bill ID: {bill_id}</h2>
                <p>Dear {title} {last_name},</p>
                <p>Thank you for choosing our tour. Below are the details you provided:</p>
                <div class="details">
                    <div><span>Title:</span> <p>{title}</p></div>
                    <div><span>First Name:</span> <p>{first_name}</p></div>
                    <div><span>Last Name:</span> <p>{last_name}</p></div>
                    <div><span>Tour Name:</span> <p>{tour_name}</p></div>
                    <div><span>Number of Children:</span> <p>{num_children}</p></div>
                    <div><span>Number of Adults:</span> <p>{num_adults}</p></div>
                    <div><span>Total Price:</span> <p>{total_price}$</p></div>
                    <div><span>Date of Departure:</span> <p>{date_departure}</p></div>
                </div>
                <p>We will get back to you soon with further details.</p>
                <div class="footer">
                    <p>Best regards,<br>Your Tour Team</p>
                </div>
            </div>
        </body>
        </html>
        """

        subject = "Tour Booking Confirmation"

        send_mail(SENDER_EMAIL, receiver_email, MAIL_PASSWORD, subject, body)

        return 'Mail Sent Successfully'