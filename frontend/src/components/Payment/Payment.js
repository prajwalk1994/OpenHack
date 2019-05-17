import React, { Component } from 'react';
import {Link,Redirect} from "react-router-dom";
class Payment extends Component {

    constructor(props) {
        super(props);
        this.state = {
            price: 100,
            discount:0,
            redirectVar:""
        }
    }

    makePayment = (e) => {
        e.preventDefault();
        alert("Payment email sent!")
        this.setState({
            redirectVar:<Redirect to="/submission"></Redirect>
        })
    }

    render() {
        return (
            <div className="">
                <center>
                    {this.state.redirectVar}
                    {/* PAYMENT CARD START */}
                    <form class="payment container">
                        <body className="col-sm-6">
                            <div class="panel panel-default credit-card-box">
                                <div class="panel-heading display-table">
                                    <div class="row display-tr">
                                        <h3 class="panel-title display-td">Payment Details</h3>
                                        <img class="img-responsive pull-right" src="http://i76.imgup.net/accepted_c22e0.png" />
                                    </div>
                                </div>
                            </div>
                            <div className="row justify-content-center">
                                <label className="col-sm-2">Price</label>
                                <input className="form-control col-sm-4" type="text" name="price" value={this.state.price} disabled></input>
                            </div>
                            <div className="row justify-content-center">
                                <label className="col-sm-2" >Discount</label>
                                <input className="form-control col-sm-4" type="text" name="discount" value={this.state.discount} disabled></input>
                            </div>
                            <div className="row justify-content-center">
                                <label className="col-sm-2" >Total</label>
                                <input className="form-control col-sm-4" type="text" name="discount" value={this.state.discount} disabled></input>
                            </div>
                            <label for="cardNumber">CARD NUMBER</label>
                            <input type="number" maxlength="16" class="form-control" name="cardNumber" placeholder="Valid Card Number" autocomplete="cc-number" required autofocus />
                            <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                            <label for="cardExpir">EXPIRATION DATE</label>
                            <input type="number" maxlength="5" class="form-control" name="cardExpiry" placeholder="MM / YY" autocomplete="cc-exp" required />
                            <label for="cardCVC">CV CODE</label>
                            <input type="number" maxlength="3" class="form-control" name="cardCVC" placeholder="CVV" autocomplete="cc-csc" required />
                        </body>
                    </form>
                    {/* PAYMENT CARD END */}
                    <div className="row justify-content-center">
                        <button className="btn btn-primary" name="pay" onClick={this.makePayment} >Make payment</button>
                    </div>
                </center>
            </div>
        );
    }
}

export default Payment;