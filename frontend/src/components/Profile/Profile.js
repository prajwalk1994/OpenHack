import React, { Component } from 'react';
import './Profile.css';
import axios from 'axios';
import Navbar from '../Navbar/Navbar';
import url from '../../config/config'
import { Link } from 'react-router-dom';


class Profile extends Component {

    constructor(props) {
        super(props);


        console.log("Inside Profile");

        this.state = {
            userId: 1,
            name: "",
            username: "",
            businessTitle: "",
            // organization: "",
            aboutMe: "",
            address: {
                state: "",
                city: "",
                zip: "",
                street: "",
            },
            myOrganizations: ["Org1", "Org2", "Org3"],
            myHackathons: ["hack1", "hack2", "hack3"],
            ListOfOrgs: ["Org1", "Org2", "Org3", "Org4", "Org5"]
        }
    }

    componentDidMount = () => {

        axios.get(url + "/organizationMember/?userId=" + this.state.userId)
            .then((response) => {
                var tempOrg = []
                for (let item of response.data) {
                    if (item.approval == "Yes") {
                        tempOrg.push(item.organization.name)
                    }
                }
                this.setState({
                    myOrganizations: tempOrg,
                })
            })
            .catch((error) => {
                console.log("Error ", error);
            })

        axios.get(url + "/profile/" + this.state.userId)
            .then((response) => {

                this.setState({
                    ...response.data
                })
                console.log(response)
            })
            .catch((err) => {
                console.log("err", err)
            })
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleAddress = (e) => {
        this.setState({
            address: {
                ...this.state.address,
                [e.target.name]: e.target.value
            }
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.state)
        axios.post(url + "/profile/" + this.state.userId, this.state)
            .then((response) => {
                this.setState({
                    ...response.data,
                })
                console.log("response", response);
                alert("Profile Updated Successfully");
            })
            .catch((err) => {
                console.log(err);
                alert("Error Updating Profile");
            })
    }


    render() {

        var organizationsDiv = (<div>No Organizations</div>);
        var organizationsDiv = this.state.myOrganizations.map((item) => {
            //console.log(item)
            return (
                <div className="row">
                    <label className="col-sm-8">{item}</label>
                    <button className="btn btn-danger col-sm-4">leave</button>
                </div>
            )
        })
        var HackathonsDiv = (<div>No Organizations</div>);
        var HackathonsDiv = this.state.myHackathons.map((item) => {
            //console.log(item)
            return (
                <div className="row">
                    <label className="col-sm-8">{item}</label>
                    <button className="btn btn-primary col-sm-4">submit</button>
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
                            <input type="text" className="form-control" onChange={this.handleChange} name="aboutMe" value={this.state.aboutMe} placeholder="aboutMe" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleAddress} name="street" value={this.state.address.street} placeholder="street" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleAddress} name="city" value={this.state.address.city} placeholder="city" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleAddress} name="zip" value={this.state.address.zip} placeholder="zip" />
                        </div>
                        <div className="mb-4">
                            <input type="text" className="form-control" onChange={this.handleAddress} name="state" value={this.state.address.state} placeholder="state" />
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
                        <div >
                            <Link to="/searchOrgs"> <button className="btn btn-primary" onClick={this.searchOrgnizations}>search organizations</button></Link>
                        </div>
                        <div>
                            <label> </label>
                        </div>
                        <div>
                            <Link to="/createOrg"><button className="btn btn-primary" onClick={this.createOrg}>create organization</button></Link>
                        </div>
                        <div>
                            <label> </label>
                        </div>
                        <div>
                            <Link to="/membershipApprovals"> <button className="btn btn-primary" onClick={this.approveMembership}>Approve Membership</button></Link>
                        </div>
                    </div>
                    <div className="col-sm-4 border border-1">
                        <div>
                            <h3>My Hackathons</h3>
                        </div>
                        <div>
                            {HackathonsDiv}
                        </div>
                        <div className="row justify-content-center">
                            <Link to="/searchHackathons"><button className="btn btn-primary" onClick={this.searchHackathon}>search Hackathons</button></Link>
                        </div>
                        {/* <div>
                            <Link to="/createHackathon"><button className="btn btn-primary" onClick={this.createHackathon}>create Hackathon</button></Link>
                        </div>
                        <div>
                            <label> </label>
                        </div> */}
                        <div>
                           <Link to="/judge"> <button className="btn btn-primary" onClick={this.judgehackathon}>Judge Hackathon</button></Link>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile;