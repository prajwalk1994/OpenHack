import React, { Component } from 'react';

class Judge extends Component {
    constructor(props) {
        super(props);
        this.state = {
            urls: ["localhost1", "localhost2", "localhost3"],
            points:[2,3,4]
        }
    }

    judge = (e) => {
        e.preventDefault();
    }
    render() {

        var urlList = "";
        urlList = this.state.urls.map((item,i)=>{
            return(
                <div className="row justify-content-start">
                <label className="col-sm-8">{item}</label>
                <input className="form-control col-sm-1" type="text" name="price" value={this.state.points[i]}></input>
            </div>
            )
        })

        return (
            <div className="container">
                <h3>Payment</h3>
                <center>
                    <div className="row justify-content-start">
                        <h4 className="col-sm-8">URL</h4>
                        <h4 className="col-sm-1">POINTS</h4>
                    </div>
                    {urlList}
                    <div className="row justify-content-center">
                        <button className="btn btn-primary" name="judge" onClick={this.judge} >DONE</button>
                    </div>
                </center>
            </div>
        );
    }
}

export default Judge;