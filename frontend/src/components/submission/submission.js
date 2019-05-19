import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config'


class submission extends Component {
    constructor(props) {
        super(props);
        this.state = {
            url: "",
            teamid: "",
            hackathonId: localStorage.getItem("hackathonid"),
            teamid: localStorage.getItem("teamid"),
        }
    }

    componentDidMount = () => {

    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    checkPayment = () => {
        alert("Submission successful!")
        // Axios.get(url+"/checkPayment",6)
        // .then((response)=>{
        //     console.log(response.data)
        //     Axios.post(url+"/submission").then(res=>{
        //         console.log(res.data)
        //     })
        // })
        // .catch((err) => {
        //     if(err.response){
        //     console.log("errror", err.response.data)
        //     }else{
        //         alert("Something went wrong!")
        //     }
        // })
    }

    doSubmission = (e) => {
        e.preventDefault();


        //check payment if all team members has made
        this.checkPayment();


        Axios.get(url + "/submission/" + this.state.teamid + "/" + this.state.hackathonId)
            .then((response) => {
                console.log(response.data)
                console.log("state after response", this.state)
            })
            .catch((err) => {
                if (err.response) {
                    console.log("errror", err.response)
                } else {
                    alert("something went wrong")
                }
            })
    }
    render() {
        return (
            <div className="container">
                <center>
                    <div className="row col-sm-6 formContainer justify-content-center">
                        <div className="row">
                            <h3>Hackathon Submission</h3>
                        </div>
                        <div className="row">
                            submission details if any
                        </div>
                        <div className="row">
                            <label className="col-sm-3">CODE URL</label>
                            <input className="form-control col-sm-9" type="text" name="url" onChange={this.handleChange} value={this.state.url}></input>
                        </div>
                        <div className="row">
                            <button className="form_element btn btn_login" name="pay" onClick={this.doSubmission}>Submit Code URL</button>
                        </div>
                    </div>
                </center>
            </div>
        );
    }
}

export default submission;