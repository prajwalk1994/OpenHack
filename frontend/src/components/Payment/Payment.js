import React, { Component } from 'react';
import { Link, Redirect } from "react-router-dom";
import Axios from 'axios';
import url from '../../config/config';

class Payment extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userId:localStorage.getItem("userid"),
            tempHackId:localStorage.getItem("tempHackId"),
            tempRegFee:localStorage.getItem("tempRegFee"),
            price: 0,
            discount: 0,
            redirectVar: ""
        }
    }

    componentDidMount=(e)=>{
        Axios.get(url + `/sponsorDiscount/${this.state.userId}/${this.state.tempHackId}`)
            .then((response) => {
                console.log("response", response.data);
                this.setState({
                    discount:response.data
                })
            })
            .catch((err) => {
                console.log(err);
                alert(err.response.data);
            })
    }

    makePayment = (e) => {
        e.preventDefault();
        alert("Payment email sent!")
        
        this.setState({
            redirectVar: <Redirect to="/submission"></Redirect>
        })
    }

    render() {
        return (
            <div className="">
                <center>
                    {this.state.redirectVar}
                    {/* PAYMENT CARD START */}
                    <form class="payment container">
                        <div className="col-sm-6 formContainer" style={{ margin: "25px" }}>
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
                                <input className="form-control col-sm-4" type="text" name="price" value={this.state.tempRegFee} disabled></input>
                            </div>
                            <div className="row justify-content-center">
                                <label className="col-sm-2" >Discount</label>
                                <input className="form-control col-sm-4" type="text" name="discount" value={this.state.discount} disabled></input>
                            </div>
                            <div className="row justify-content-center">
                                <label className="col-sm-2" >Total</label>
                                <input className="form-control col-sm-4" type="text" name="discount" value={this.state.tempRegFee*(1-this.state.discount/100)} disabled></input>
                            </div>
                            <div className="row justify-content-right">
                                <label for="cardNumber" className="col-sm-4">CARD NUMBER</label>
                                <input type="number" maxlength="16" className="form-control col-sm-8" name="cardNumber" placeholder="Valid Card Number" autocomplete="cc-number" required autofocus />
                            </div>

                            <div className="row justify-content-right">
                                <label for="cardNumber" className="col-sm-4">EXPIRATION DATE</label>
                                <input type="date" maxlength="5" class="form-control col-sm-8" name="cardExpiry" placeholder="MM / YY" autocomplete="cc-exp" required />
                            </div>
                            <div className="row justify-content-right">
                                <label for="cardNumber" className="col-sm-4">CVV</label>
                                <input type="number" maxlength="3" class="form-contro col-sm-8" name="cardCVC" placeholder="CVV" autocomplete="cc-csc" required />
                            </div>
                            <div className="row justify-content-center">
                                <button className="form_element btn btn_login" name="pay" onClick={this.makePayment} >Make payment</button>
                            </div>
                        </div>

                    </form>
                    {/* PAYMENT CARD END */}

                </center>
            </div>
        );
    }
}

export default Payment;