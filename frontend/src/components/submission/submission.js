import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config'


class submission extends Component {
    constructor(props) {
        super(props);
        this.state = {
            url: "",
            teamid: "",
            hackathonId: localStorage.getItem("tempHackId"),
            teamid: localStorage.getItem("tempTeamId"),
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

    }

    doSubmission = (e) => {
        e.preventDefault();

        if (this.state.url.length == 0) {
            alert("Url shouldn't be empty!")
        }
        else {
            Axios.get(url + `/checkTeamPayment/${this.state.hackathonId}/${this.state.teamid}`)
                .then((response) => {
                    console.log(response.data)
                    Axios.post(url + `/submission/${this.state.teamid}/${this.state.hackathonId}/${this.state.url}`)
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
                })
                .catch((err) => {
                    if (err.response) {
                        console.log("errror", err.response.data)
                    } else {
                        alert("Something went wrong!")
                    }
                })
        }



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