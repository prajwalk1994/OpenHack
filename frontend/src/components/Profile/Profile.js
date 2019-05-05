import React, { Component } from 'react';
import './Profile.css';
import axios from 'axios';
import Navbar from '../Navbar/Navbar';

class Profile extends Component {

    constructor(props) {
        super(props);


        console.log("Inside Profile");

        this.state = {
            name: "John Doe",
            username: "usrname",
            businessTitle: "business title",
            organization: "organization",
            aboutMe: "hello world",
            address: "pakistan",
            myOrganizations: ["Org1", "Org2", "Org3"],
            myHackathons: ["hack1", "hack2", "hack3"],
            ListOfOrgs: ["Org1", "Org2", "Org3", "Org4", "Org5"]
        }
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
    }

    render() {

        var organizationsDiv = (<div>No Organizations</div>);
        var organizationsDiv = this.state.myOrganizations.map((item) => {
            console.log(item)
            return (
                <div className="row">
                    <label className="col-sm-8">{item}</label>
                    <button className="btn btn-danger col-sm-4">leave</button>
                </div>
            )
        })
        var HackathonsDiv = (<div>No Organizations</div>);
        var HackathonsDiv = this.state.myHackathons.map((item) => {
            console.log(item)
            return (
                <div className="row">
                    <label className="col-sm-8">{item}</label>
                    <button className="btn btn-danger col-sm-4">leave</button>
                </div>
            )
        })

        return (
            <div>
                <Navbar></Navbar>
                <div className="row parentRow">
                    <div className="col-sm-4 border border-1">
                        <div className="mb-4">
                            <img className="profilePic" src={require("../../images/user.png")} ></img>
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleChange} name="name" value={this.state.name} placeholder="name" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleChange} name="businessTitle" value={this.state.businessTitle} placeholder="businessTitle" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleChange} name="organization" value={this.state.organization} placeholder="organization" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleChange} name="aboutMe" value={this.state.aboutMe} placeholder="aboutMe" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleChange} name="address" value={this.state.address} placeholder="address" />
                        </div>
                        <div>
                            <button className="btn btn-primary" onClick={this.handleSubmit}>Submit</button>
                        </div>
                    </div>
                    <div className="col-sm-4 border border-1">
                        <div>
                            <h3>My organizations</h3>
                        </div>
                        <div>
                            {organizationsDiv}
                        </div>
                        <div className="row">
                            <div className="col-sm-8">
                                <input type="text" className="form-control" placeholder="search Organizations"></input>
                            </div>
                            <div className="col-sm-4">
                                <button className="btn btn-primary" onClick={this.searchOrgnizations}>search</button>
                            </div>
                        </div>
                        <div>
                            <button className="btn btn-primary" onClick={this.createOrg}>create organization</button>
                        </div>
                        <div>
                            <label> </label>
                        </div>
                        <div>
                            <button className="btn btn-primary" onClick={this.approveMembership}>Approve Membership</button>
                        </div>
                    </div>
                    <div className="col-sm-4 border border-1">
                        <div>
                            <h3>My Hackathons</h3>
                        </div>
                        <div>
                            {HackathonsDiv}
                        </div>
                        <div className="row">
                            <div className="col-sm-8">
                                <input type="text" className="form-control" placeholder="search hackathons"></input>
                            </div>
                            <div className="col-sm-4">
                                <button className="btn btn-primary" onClick={this.searchHackathon}>search</button>
                            </div>
                        </div>
                        <div>
                            <button className="btn btn-primary" onClick={this.judgehackathon}>Judge Hackathon</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile;