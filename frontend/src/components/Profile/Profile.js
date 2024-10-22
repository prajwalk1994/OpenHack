import React, { Component } from 'react';
import './Profile.css';
import axios from 'axios';
import Navbar from '../Navbar/Navbar';
import url from '../../config/config'
import { Link, Redirect } from "react-router-dom";
import Axios from 'axios';
import Payment from '../Payment/Payment'

class Profile extends Component {

    constructor(props) {
        super(props);

        console.log("Inside Profile");

        this.state = {
            userId: localStorage.getItem("userid"),
            name: "",
            username: "",
            businessTitle: "",
            screenName: localStorage.getItem("username"),
            // organization: "",
            aboutMe: "",
            address: {
                state: "",
                city: "",
                zip: "",
                street: "",
            },
            myOrganizations: [],
            myOrganizationIds: [],
            myHackathons: [],
            ListOfOrgs: []
        }
    }

    componentDidMount = async () => {

        // console.log("**************************cfhgjhk*")

        await axios.get(url + "/hackathonsByUser/" + this.state.userId)
            .then((response) => {
                // console.log("***************************")
                let hackathons = response.data;
                console.log(hackathons);
                var temphackslist = []
                hackathons.map((hackathon) => {
                    temphackslist.push(hackathon)

                })
                this.setState({
                    myHackathons: temphackslist,
                })
            })
            .catch((error) => {
                console.log("Error", error);
            })

        axios.get(url + "/organizationMember/?userId=" + this.state.userId)
            .then((response) => {
                console.log("Org details: ", response.data)
                var tempOrg = []
                var tempOrgIds = []
                for (let item of response.data) {
                    if (item.approval == "Yes") {
                        tempOrg.push(item.organization.name)
                        tempOrgIds.push(item.id)
                    }
                }
                this.setState({
                    myOrganizations: tempOrg,
                    myOrganizationIds: tempOrgIds
                })
            })
            .catch((error) => {
                console.log("Error ", error);
            })

        axios.get(url + "/profile/" + this.state.userId)
            .then((response) => {
                console.log(response.data)
                this.setState({
                    ...response.data
                })
                console.log(response)
            })
            .catch((err) => {
                console.log("err", err)
            })

        // axios.get(url + "/hackathon/?userId=" + localStorage.getItem("userid"))
        //     .then((response) => {
        //         var tempHack = []
        //         for (let item of response.data) {
        //             if (item.approval == "Yes") {
        //                 tempHack.push(item.Hackathon.name)
        //             }
        //         }
        //         this.setState({
        //             myHackathons: tempHack,
        //         })
        //     })
        //     .catch((error) => {
        //         console.log("Error ", error);
        //     })


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

    createOrg = async (e) => {
        if (localStorage.getItem("role") != "Admin") {
            if (localStorage.getItem("verified")) {
                await this.setState({
                    redirectTo: <Redirect to="/createOrg" />
                })
            } else {
                alert("User not verified!");
            }
        }
        else {
            alert("Admin cannot create Organization")
        }
    }

    searchOrgnizations = async (e) => {
        if (localStorage.getItem("verified")) {
            await this.setState({
                redirectTo: <Redirect to="/searchOrgs" />
            })
        } else {
            alert("User not verified!");
        }
    }

    searchHackathon = async (e) => {
        if (localStorage.getItem("verified")) {
            await this.setState({
                redirectTo: <Redirect to="/searchHackathons" />
            })
        } else {
            alert("User not verified!");
        }
    }

    approveMembership = async (e) => {
        if (localStorage.getItem("verified")) {
            await this.setState({
                redirectTo: <Redirect to="/membershipApprovals" />
            })
        } else {
            alert("User not verified!");
        }
    }

    judgehackathon = async (e) => {
        if (localStorage.getItem("verified")) {
            await this.setState({
                redirectTo: <Redirect to="/judge" />
            })
        } else {
            alert("User not verified!");
        }
    }

    createHackathon = async (e) => {
        if (localStorage.getItem("role") == "Admin") {
            if (localStorage.getItem("verified")) {
                await this.setState({
                    redirectTo: <Redirect to="/createHackathon" />
                })
            } else {
                alert("User not verified!");
            }
        }
        else {
            alert("Only Admin can create Hackathon")
        }
    }

    submitcode = async (e, id, arg2, arg3) => {
        await localStorage.setItem("tempHackId", id)
        await localStorage.setItem("tempRegFee", arg2)
        await localStorage.setItem("tempTeamId", arg3)

        await localStorage.setItem("submissionHackathonId",id)
        await this.setState({
            redirectTo: <Redirect to="/Submission" />
        })
    }

    handleLeaveOrg = (e, id) => {
        console.log(id)
        axios.post(url + `/leaveOrganization/${this.state.userId}/${this.state.myOrganizationIds[id]}`)
            .then((response) => {
                console.log("response", response.data);
                window.location.reload();
            })
            .catch((err) => {
                console.log(err);
                alert("Error Updating Profile");
            })
    }

    render() {
        var organizationsDiv = (<div>No Organizations</div>);
        if (this.state.myOrganizations.length > 0) {
            var organizationsDiv = this.state.myOrganizations.map((item, index) => {
                console.log(item)
                return (
                    <div className="row">
                        <label className="col-sm-8">{item}</label>
                        <button className="btn btn-danger col-sm-4" onClick={(e) => this.handleLeaveOrg(e, (index))}>leave</button>
                    </div>
                )
            })
        } else {
            var organizationsDiv = (
                <div className="row justify-content-center">
                    No Organizations now
                </div>
            )
        }

        var HackathonsDiv = (<div>No Organizations</div>);
        if (this.state.myHackathons.length > 0) {
            HackathonsDiv = this.state.myHackathons.map((item) => {
                console.log(item)
                return (
                    <div className="row">
                        <label className="col-sm-6">{item.hackId.name}</label>
                        {(item.submissionUrl) ? ((item.submissionUrl.length == 0) ? <button className="btn btn_login col-sm-6" onClick={(e) => { this.submitcode(e, item.hackId.id, item.hackId.regFee, item.teamId.id) }}>submit </button> : <button className="btn btn-dark col-sm-6" disabled>Submitted</button>) : <button className="btn btn_login col-sm-6" onClick={(e) => { this.submitcode(e, item.hackId.id, item.hackId.regFee, item.teamId.id) }}>submit </button>}
                    </div>
                )
            })
        } else {
            HackathonsDiv = (
                <div className="row justify-content-center">
                    No Hackathons now
                </div>
            )
        }

        return (
            <div className="scroll" style={{ marginLeft: "5%" }}>
                <div>
                    {this.state.redirectTo}
                </div>
                <div className="row parentRow">
                    <div className="formContainer col-sm-3" style={{ margin: "15px" }}>
                        <div className="mb-4">
                            <img className="profilePic" src={require("../../images/user.png")} ></img>
                            <h5>{this.state.screenName}</h5>
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
                            <button className="form_element btn_login btn btn-lg btn-block" onClick={this.handleSubmit}>Submit</button>
                        </div>
                    </div>
                    <div className="row col-sm-3" style={{ margin: "15px" }}>
                        <div className="formContainer">
                            <div>
                                <h3>My Hackathons List</h3>
                            </div>
                            <div>
                                {HackathonsDiv}
                            </div>
                        </div>
                        <div className="formContainer">
                            <div>
                                <h3>My organizations List</h3>
                            </div>
                            <div>
                                {organizationsDiv}
                            </div>
                        </div>
                    </div>
                    <div className="row col-sm-4" style={{ margin: "15px" }}>
                        <div className="formContainer" style={{ paddingLeft: "30px", paddingRight: "30px" }}>
                            <div className="">
                                <button className="form_element btn_login btn btn-block" onClick={this.searchHackathon}>search Hackathons</button>
                            </div>
                            <div>
                                <button className="form_element btn_login btn btn-block" onClick={this.createHackathon}>create Hackathon</button>
                            </div>
                            <div>
                                <button className="form_element btn_login btn btn-block" onClick={this.judgehackathon}>Judge Hackathon</button>
                            </div>
                        </div>
                        <div className="formContainer">
                            <div>
                                <button className="form_element btn_login btn btn-block" onClick={this.searchOrgnizations}>search organizations</button>
                            </div>
                            <div>
                                <button className="form_element btn_login btn btn-block" onClick={this.createOrg}>create organization</button>
                            </div>
                            <div>
                                <button className="form_element btn_login btn btn-block" onClick={this.approveMembership}>Approve Membership</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile;