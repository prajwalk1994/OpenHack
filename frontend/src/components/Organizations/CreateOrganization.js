import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config'


class CreateOrganization extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ownerid: localStorage.getItem("userid"),
            address: {
                state: "",
                city: "",
                zip: "",
                street: "",
            }
        }
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        // Get owner id from local storage
        console.log("state before axios", this.state)
        const data = {
            ...this.state
        }
        
        Axios.post(url + "/organization?ownerId=" + this.state.ownerid, data)
            .then((res) => {
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })

    }
    render() {
        return (
            <div>
                <div className="container mt-5">
                    <h3>Create Organization</h3>
                    <center>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" onChange={this.handleChange} type="text" name="name" placeholder="Orgnization Name"></input>
                        </div>
                        {/* <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" onChange={this.handleChange} name="owner" placeholder="Orgnization Owner"></input>
                        </div> */}
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="textArea" onChange={this.handleChange} name="description" placeholder="Orgnization Description"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" onChange={this.handleChange} name="city" placeholder="Orgnization Address"></input>
                        </div>
                        <div className="row justify-content-center">
                            <button className="btn btn-primary" name="pay" onClick={this.handleSubmit}>Submit</button>
                        </div>
                    </center>
                </div>
            </div>
        );
    }
}

export default CreateOrganization;