import React, { Component } from 'react';

class Payment extends Component {

    constructor(props) {
        super(props);
        this.state = {
            price: 0,
            discount: 0
        }
    }

    makePayment=(e)=>{
        e.preventDefault();
    }

    render() {
        return (
            <div className="container">
                <h3>Payment</h3>
                <center>
                    <div className="row justify-content-center">
                        <label className="col-sm-2">Price</label>
                        <input className="form-control col-sm-4" type="text" name="price" value={this.state.price} disabled></input>
                    </div>
                    <div className="row justify-content-center">
                        <label className="col-sm-2" >Discount</label>
                        <input className="form-control col-sm-4" type="text" name="discount" value={this.state.discount} disabled></input>
                    </div>
                    <div className="row justify-content-center">
                        <button className="btn btn-primary" name="pay" onClick={this.makePayment} >Make payment</button>
                    </div>
                </center>
            </div>
        );
    }
}

export default Payment;