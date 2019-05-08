import React, { Component } from 'react';

class MembershipApprovals extends Component {
    constructor(props){
        super(props);
        this.state={
            username:["ranjith","prajwal","vinay"],
            orgName:["sjsu","isss","isis"],
            status:[]
        }
    }

    handleSubmit = (e)=>{
        e.preventDefault();
    }

    render() {
        var MembershipRequests = "";
        MembershipRequests = this.state.username.map((item,i)=>{
            return(
                <div className="row justify-content-center">
                    <label className="col-sm-3">{item}</label>
                    <label className="col-sm-3">{this.state.orgName[i]}</label>
                    <button className="btn btn-success mr-2">Approve</button>
                    <button className="btn btn-danger">Deny</button>
                </div>
            )
        })

        return (
            <div className="container">
            <h3>Membership Approvals</h3>
                <div className="row justify-content-center">
                    <h6 className="col-sm-3">Username</h6>
                    <h6 className="col-sm-3">Organization name</h6>
                </div>
                {MembershipRequests}
            </div>
        );
    }
}

export default MembershipApprovals;