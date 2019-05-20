import React, { Component } from 'react';
import url from '../../config/config'
import axios from 'axios';

class searchOrganizations extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userid: localStorage.getItem("userid"),
            organizationSearched: "",
            organizationSearchedId: "",
            organizationsList: ["happy", "haoppy"],
            // organizationsIds:[],
            AllOrgsData: "",
            visibility: false
        }
    }

    componentDidMount = () => {
        //get all organizations list
        axios.get(url + "/organizations")
            .then((response) => {
                console.log(response.data)
                this.setState({
                    AllOrgsData: response.data
                })

                var tempOrg = []
                // var tempOrgIds=[]
                for (let i of response.data) {
                    tempOrg.push(i.name)
                    // tempOrgIds.push(i.id)
                }
                this.setState({
                    organizationsList: tempOrg,
                    // organizationsIds:tempOrgIds
                })
                console.log("state after setting orgs", this.state)
            })
            .catch((err) => {
                if (err.response) {
                    console.log("err", err.response)
                } else {
                    alert("something went wrong")
                }
            })
    }

    handleSearchChange = (e) => {
        this.setState({
            organizationSearchedtemp: e.target.value
        })
    }

    searchOrgnizations = async (e) => {
        e.preventDefault();
        await this.setState({
            organizationSearched: this.state.organizationSearchedtemp
        })

        for (let i = 0; i < this.state.organizationsList.length; i++) {
            if (this.state.organizationSearched == this.state.organizationsList[i]) {
                this.setState({
                    organizationSearchedId:i
                })
                // await alert("org found at " + i)
            } else {
                await alert("organization not found")
            }
        }

    }

    sendJoinRequest = (e, organizationId) => {
        //send id of organization selected to backend
        e.preventDefault();
        console.log("sending join request...")
        axios.post(url + "/organizationMember/" + this.state.userid + "/" + organizationId)
            .then((response) => {
                console.log(response.data)
            })
            .catch((err) => {
                if (err.response) {
                    console.log("err", err.response)
                } else {
                    alert("something went wrong")
                }
            })
    }

    render() {
        var organizationsListDiv = "";
        organizationsListDiv = this.state.organizationsList.map((item, index) => {
            return (
                <div className="row">
                    <h5 className="col-sm-6 mt-4">{item}</h5>
                    <button className=" col-sm-6 form_element btn btn_login" onClick={(e) => this.sendJoinRequest(e, (index + 1))}>join</button>
                </div>
            )
        })

        var datalistOrgs = "";
        datalistOrgs = this.state.organizationsList.map((item, index) => {
            return (
                <div>
                    <option value={item} />
                </div>
            )
        })

        var searchedOrgDiv = "";
        searchedOrgDiv = (
            <div className="row">
                <h4 className="col-sm-3" name="searchedName">{this.state.organizationSearched}</h4>
                <button className="col-sm-9  btn btn_login" onClick={this.sendJoinRequest}>Join</button>
            </div>
        )

        return (
            <div className="row justify-content-center">
                <div className=" justify-content-center formContainer">
                    <h3>Search Organizations</h3>
                    <div className="row justify-content-center">
                        <input list="organizations" type="text" onChange={this.handleSearchChange} className="form-control mb-4" name="searchOrg" placeholder="search organizations"></input>
                        <datalist id="organizations">
                            {datalistOrgs}
                        </datalist>
                    </div>
                    <div className="row justify-content-center">
                        <button className="btn btn_login" onClick={this.searchOrgnizations}>Search</button>
                    </div>
                    <div className="row justify-content-center">
                        <h4 className="col-sm-9">{this.state.organizationSearched}</h4>
                        {this.state.organizationSearched ? <button className="btn btn_login col-sm-3" onClick={(e) => this.sendJoinRequest(e, (this.state.organizationSearchedId+1))}>join</button> : <div></div>}
                    </div>
                </div>
                <div className="justify-content-center formContainer ml-5">
                    {!this.state.visibility ? <h3>List of all Organizations</h3> : <div></div>}
                    <div className="">
                        {!this.state.visibility ? organizationsListDiv : searchedOrgDiv}
                    </div>
                </div>
            </div>
        );
    }
}

export default searchOrganizations;