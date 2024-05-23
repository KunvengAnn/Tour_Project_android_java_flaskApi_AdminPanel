$(document).ready(function () {
    // Btn PackageTOur
    $("#idPackageTour").click(function () {
        getToursData_Table();

    });

    //Btn Customer
    $('#idCustomer').click(function () {
        var Content = "<p>This on Customer.</p>";
        $("#content").html(Content);
    });

    $('#idPayment').click(function () {
        getPaymentContent();
    });
    function getPaymentContent() {
        $.get("http://127.0.0.1:5000/api/payment")
            .done(function (data) {
                if (!data || !data.payments) {
                    console.error("Received null or invalid response");
                    $("#content").html("<p>Error: Failed to fetch payment data</p>");
                    return;
                }

                var payments = data.payments;
                var tablePayment = `
                    <table id="Payment-tb" class="table table-hover">
                        <thead>
                            <tr align="center" class="table-info fw-bold thead-danger">
                                <th>STT</th>
                                <th>Booking ID</th>
                                <th>Payment Date</th>
                                <th>Amount Paid</th>
                                <th>Payment Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                `;

                for (var i = 0; i < payments.length; i++) {
                    var item = payments[i];
                    var actionButtons = '';
                    if (item.payment_status === "false") {
                        actionButtons = `
                            <button class="btn btn-warning submit-payment" data-payment-id="${item.payment_id}" data-booking-id="${item.booking_id}">Submit</button>
                            <button class="btn btn-primary update-payment" data-payment-id="${item.payment_id}">Update</button>
                        `;
                    } else {
                        actionButtons = `
                            <button class="btn btn-danger delete-payment" data-payment-id="${item.payment_id}">Delete</button>
                            <button class="btn btn-primary update-payment" data-payment-id="${item.payment_id}">Update</button>
                        `;
                    }

                    var status = item.payment_status;
                    if (status === "true") {
                        status = "success";
                    } else {
                        status = "not yet";
                    }
                    tablePayment += `
                        <tr align="center" data-booking-id="${item.booking_id}">
                        <td>${i + 1}</td>
                        <td>${item.booking_id}</td>
                        <td>${item.payment_date}</td>
                        <td>${item.amount_paid}$</td>
                        <td>${status}</td>
                        <td>${actionButtons}</td>
                    </tr>
                    `;
                }

                tablePayment += `</tbody></table>`;

                $("#content").html(tablePayment);
                // Attach event listeners
                $('#Payment-tb').on('click', 'tbody tr', function () {
                    var bookingId = $(this).data('booking-id');
                    showDetailPayment(bookingId);
                });

                $(".update-payment").click(function () {
                    event.stopPropagation(); // Prevent the row click event from firing
                    var paymentId = $(this).data("payment-id");
                    updatePayment(paymentId);
                });

                $(".delete-payment").click(function () {
                    event.stopPropagation(); // Prevent the row click event from firing
                    var paymentId = $(this).data("payment-id");
                    deletePayment(paymentId);
                });

                $(".submit-payment").click(function () {
                    event.stopPropagation(); // Prevent the row click event from firing
                    var paymentId = $(this).data("payment-id");
                    var bookingId = $(this).data("booking-id");
                    SubmitPayment(paymentId, bookingId);
                });
            })
            .fail(function () {
                console.error("Failed to fetch data from the endpoint.");
                $("#content").html("<p>Error: Failed to fetch payment data</p>");
            });
    }
    function showDetailPayment(bookingId) {
        $.get("http://127.0.0.1:5000/api/booking/" + bookingId, function (response) {
            var booking = response.booking;
            var tablePayment = `
                <table id="Payment-tb" class="table table-hover">
                    <thead>
                        <tr align="center" class="table-info fw-bold thead-danger">
                            <th>STT</th>
                            <th>Title</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Tour Name</th>
                            <th>Email</th>
                            <th>Number of Children</th>
                            <th>Number of Adults</th>
                            <th>Customer Phone</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr align="center">
                            <td>1</td>
                            <td>${booking.title}</td>
                            <td>${booking.first_name}</td>
                            <td>${booking.last_name}</td>
                            <td>${booking.tour_name}</td>  
                            <td>${booking.customer_email}</td>
                            <td>${booking.number_of_child}</td>
                            <td>${booking.number_of_adult}</td>
                            <td>${booking.customer_phone}</td>
                            <td>
                                <button class="btn btn-danger" onclick="deleteBooking(${bookingId})">Delete</button>
                                <button class="btn btn-primary" onclick="updateBooking(${bookingId})">Update</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            `;

            // Show the table within a jQuery Confirm dialog
            $.confirm({
                title: 'Booking Details',
                content: tablePayment,
                columnClass: 'l',
                buttons: {
                    close: {
                        text: 'Close',
                        btnClass: 'btn btn-secondary',
                        action: function () {
                        }
                    }
                }
            });
        }).fail(function (xhr, status, error) {
            console.error("Error fetching booking details:", error);
        });
    }
    function showDetailPayment(bookingId) {
        $.get("http://127.0.0.1:5000/api/booking/" + bookingId, function (response) {
            var booking = response.booking;
            var tablePayment = `
                <table id="Payment-tb" class="table table-hover">
                    <thead>
                        <tr align="center" class="table-info fw-bold thead-danger">
                            <th>STT</th>
                            <th>Title</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Tour Name</th>
                            <th>Email</th>
                            <th>Number of Children</th>
                            <th>Number of Adults</th>
                            <th>Customer Phone</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr align="center">
                            <td>1</td>
                            <td>${booking.title}</td>
                            <td>${booking.first_name}</td>
                            <td>${booking.last_name}</td>
                            <td>${booking.tour_name}</td>  
                            <td>${booking.customer_email}</td>
                            <td>${booking.number_of_child}</td>
                            <td>${booking.number_of_adult}</td>
                            <td>${booking.customer_phone}</td>
                            <td>
                                <button class="btn btn-danger" onclick="deleteBooking(${bookingId})">Delete</button>
                                <button class="btn btn-primary" onclick="updateBooking(${bookingId})">Update</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            `;

            // Show the table within a jQuery Confirm dialog
            $.confirm({
                title: 'Booking Details',
                content: tablePayment,
                columnClass: 'xl',
                buttons: {
                    close: {
                        text: 'Close',
                        btnClass: 'btn btn-secondary',
                        action: function () {
                        }
                    }
                }
            });
        }).fail(function (xhr, status, error) {
            console.error("Error fetching booking details:", error);
        });
    }

    function updatePayment(paymentId) {
        $.confirm({
            title: 'Update Payment',
            content: 'Are you sure you want to update the payment with ID ' + paymentId + '?',
            columnClass: 'm',
            buttons: {
                confirm: {
                    text: 'Confirm',
                    btnClass: 'btn-blue',
                    action: function () {
                        console.log("Update confirmed for payment ID:", paymentId);
                        // Implement your update logic here
                    }
                },
                cancel: {
                    text: 'Cancel',
                    action: function () {
                        console.log("Update cancelled for payment ID:", paymentId);
                    }
                }
            }
        });
    }

    // delete action
    function deletePayment(paymentId) {
        console.log("Delete payment with ID:", paymentId);
    }

    function SubmitPayment(paymentId, bookingId) {
        $.confirm({
            title: 'Submit Payment',
            content: '<form class="needs-validation" novalidate>' +
                '<div class="form-group">' +
                '<label for="staffName">Staff Name</label>' +
                '<input type="text" class="form-control" id="staffName" required>' +
                '<div class="invalid-feedback">Please enter staff name.</div>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="staffPhone">Staff Phone</label>' +
                '<input type="text" class="form-control" id="staffPhone" required>' +
                '<div class="invalid-feedback">Please enter staff phone number.</div>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="staffAddress">Staff Address</label>' +
                '<input type="text" class="form-control" id="staffAddress" required>' +
                '<div class="invalid-feedback">Please enter staff address.</div>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="paymentStatus">Payment Status</label>' +
                '<select class="form-control" id="paymentStatus">' +
                '<option value="1">Success</option>' +
                '<option value="0">fail</option>' +
                '</select>' +
                '</div>' +
                '</form>',
            buttons: {
                submit: {
                    text: 'Submit',
                    btnClass: 'btn btn-primary',
                    action: function () {
                        var form = this.$content.find('form')[0];
                        if (!form.checkValidity()) {
                            form.classList.add('was-validated');
                            return false;
                        }

                        var staffName = $('#staffName').val();
                        var staffPhone = $('#staffPhone').val();
                        var staffAddress = $('#staffAddress').val();
                        var paymentStatus = $('#paymentStatus').val();

                        addStaff(paymentId, staffName, staffPhone, staffAddress);
                        updatePaymentStatus(paymentId, paymentStatus);
                        console.log('paymentStatus', paymentStatus);
                        if (paymentStatus == 1) {
                            getBookingDetail(bookingId); // on this will send the email bill to user customer
                        }
                        // Return false to prevent closing the dialog
                        //return false;
                    }
                },
                cancel: {
                    text: 'Cancel',
                    btnClass: 'btn btn-secondary',
                    action: function () {
                        // Do nothing, simply close the dialog
                    }
                }
            },
            onContentReady: function () {
                // $('#staffName').val(''); 
                // $('#staffPhone').val(''); 
                // $('#staffAddress').val(''); 
                // $('#paymentStatus').val('true'); 
            }
        });
    }
    function sendEmail(bookingId, receiver_email, title, first_name, last_name, tour_name, num_children, num_adults, total_price, date_departure) {
        var emailData = {
            "bill_id": bookingId,
            "receiver_email": receiver_email,
            "title": title,
            "first_name": first_name,
            "last_name": last_name,
            "tour_name": tour_name,
            "num_children": num_children,
            "num_adults": num_adults,
            "total_price": total_price,
            "date_departure": date_departure
        };

        // Use jQuery $.post to send a POST request
        $.post({
            url: "http://127.0.0.1:5000/api/email-sender",
            contentType: "application/json",
            data: JSON.stringify(emailData),
            success: function (response) {
                console.log("Email sent successfully.");
            },
            error: function (xhr, status, error) {
                console.log("Error sending email: " + xhr.responseText);
            }
        });
    }

    function getBookingDetail(bookingId) {
        $.get("http://127.0.0.1:5000/api/booking/" + bookingId, function (response) {
            console.log("response getBookingDetail", response);
            console.log("response ", response.booking.Date_of_department);
            if (response.booking != null) {
                var receiver_email = response.booking.customer_email;
                var title = response.booking.title;
                var first_name = response.booking.first_name;
                var last_name = response.booking.last_name;
                var tour_name = response.booking.tour_name;
                var num_children = response.booking.number_of_child;
                var num_adults = response.booking.number_of_adult;
                var total_price = response.booking.total_price;
                var date_departure = response.booking.Date_of_department;
                sendEmail(bookingId, receiver_email, title, first_name, last_name, tour_name, num_children, num_adults, total_price, date_departure);
            }
        }).fail(function (xhr, status, error) {
            alert("Error fetching booking details: " + error);
        });
    }


    function addStaff(payment_id, staff_name, staff_phone, staff_address) {
        const staffData = {
            payment_id: payment_id,
            staff_name: staff_name,
            staff_phone: staff_phone,
            staff_address: staff_address
        };

        // Use jQuery to send a POST request with the correct content type and JSON data
        $.post({
            url: "http://127.0.0.1:5000/api/staff",
            contentType: "application/json",
            data: JSON.stringify(staffData),
            success: function (response) {
                //console.log("Staff added successfully:", response);
                alert("Staff added successfully");
            },
            error: function (xhr, status, error) {
                //console.error("Error adding staff:",);
                alert("Staff added successfully", xhr.responseText);
            }
        });
    }


    function updatePaymentStatus(payment_id, payment_status) {
        const paymentData = {
            payment_status: payment_status
        };

        // Use jQuery $.ajax to send a PUT request
        $.ajax({
            url: "http://127.0.0.1:5000/api/payment/" + payment_id,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(paymentData),
            success: function (response) {
                //console.log("Payment updated successfully:", response);
                getPaymentContent();
            },
            error: function (xhr, status, error) {
                console.error("Error updating payment:", xhr.responseText);
            }
        });
    }



    setInterval(getPaymentInterval, 1000);
    var badgeNumber = 0;
    var previousPaymentLs = new Set();

    function getPaymentInterval() {
        $.get("http://127.0.0.1:5000/api/payment")
            .done(function (data) {
                if (data === null) {
                    console.log("Received null response");
                } else {
                    var payments = data.payments;
                    for (var i = 0; i < payments.length; i++) {
                        var item = payments[i];

                        // Update badge number based on payment status
                        if (item.payment_status === "false" && !previousPaymentLs.has(item.payment_id)) {
                            previousPaymentLs.add(item.payment_id);
                            badgeNumber++;
                            getPaymentContent();
                        } else if (item.payment_status === "true" && previousPaymentLs.has(item.payment_id)) {
                            previousPaymentLs.delete(item.payment_id);
                            badgeNumber--;
                            getPaymentContent();
                        }
                    }

                    // Update badge number
                    updateBadgeNumber();
                }
                console.log("Function invoked " + badgeNumber + " times");
            })
            .fail(function () {
                console.error("Failed to fetch data from the endpoint.");
            });
    }



    function updateBadgeNumber() {
        const $idBadges = $('#idBadges');
        if (badgeNumber > 0) {
            $idBadges.removeClass('hide-badges');
            $idBadges.text(badgeNumber);
        } else {
            $idBadges.addClass('hide-badges');
        }
    }
    //
    function getToursData_Table() {
        $.get("http://127.0.0.1:5000/api/tours", function (data) {
            var tours = data.tours;
            var tableHtml = '<table id="tourTable" class="table table-hover">' +
                '<thead>' +
                '<tr align="center" class="table-info fw-bold thead-danger">' +
                '<th>STT</th>' +
                '<th>Tour ID</th>' +
                '<th>Name</th>' +
                '<th>Description</th>' +
                '<th>Duration</th>' +
                '<th>Price</th>' +
                '<th>Location</th>' +
                '<th>Action</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>';

            for (var i = 0; i < tours.length; i++) {
                var tour = tours[i];
                tableHtml += '<tr align="center">' +
                    '<td>' + (i + 1) + '</td>' +
                    '<td>' + tour.tour_id + '</td>' +
                    '<td>' + tour.tour_name + '</td>' +
                    '<td>' + tour.tour_description + '</td>' +
                    '<td>' + tour.tour_duration + '</td>' +
                    '<td>' + tour.tour_price + '</td>' +
                    '<td>' + tour.tour_location + '</td>' +
                    '<td>' +
                    '<button class="btn btn-sm btn-primary btn-action-tour" data-action="update_tour" data-tour-id="' + tour.tour_id + '" title="Update Tour"><i class="fa-solid fa-pen"></i></button>' +
                    '<button class="btn btn-sm btn-danger btn-action-tour" data-action="delete_tour" data-tour-id="' + tour.tour_id + '" title="Delete Tour"><i class="fa-solid fa-trash"></i></button>' +
                    '</td>' +
                    '</tr>';
            }

            tableHtml += '</tbody></table>';

            // Replace the content of the #content div with the table HTML
            $('#content').html(tableHtml);
        });
    }
});