import axios from "axios";
import {useState } from "react";
import moment from "moment";
import DataTable from "react-data-table-component";

function PagingComponent() {
  const columns = [
    {
      name: "ID",
      selector: (row) => row.paymentId
    },
    {
      name: "Payment Type",
      selector: (row) => row.paymentType
    },
    {
      name: "Amount",
      selector: (row) => row.amount
    },
    {
      name: "Customer Id",
      selector: (row) => row.customerId
    },
    {
      name: "Payment Date",
      selector: (row) => moment(row.paymentDate).format("DD MM YYYY HH:mm:ss")
    },
  ];

  const callGetPaymentApi = async (customerId, paymentType, limit, page) => {
    axios
      .get("http://localhost:8080/payments", {
        params: {
          customerId: customerId,
          paymentType: paymentType,
          limit: limit,
          page: page,
        },
      })
      .then((response) => {
        console.log(response.data);
        const responseData = response.data.value;
        setData(responseData.data);
        setTotalRows(responseData.totalRecords);
      })
      .catch((error) => console.log(error));
  };

  const handlePaymentTypeChange = (event) => {
    let paymentType = event.target.value;
    setPaymentType(paymentType)
    callGetPaymentApi(customerId, paymentType, countPerPage, page)
  }

  const handleCustomerIdChange = (event) => {
    let customerId = event.target.value;
    setCustomerId(customerId)
    callGetPaymentApi(customerId, paymentType, countPerPage, page)
  }

  const handlePageChange = (page) => {
    setPage(page);
    callGetPaymentApi(customerId, paymentType, countPerPage, page)
  }

  const [countPerPage, setCountPerPage] = useState(10);
  const [totalRows, setTotalRows] = useState(0);
  const [data, setData] = useState([]);
  const [page, setPage] = useState(1);
  const [customerId, setCustomerId] = useState(1);
  const [paymentType, setPaymentType] = useState("");
  const paymentTypes = ["CASH", "CREDIT"]

  return (
    <div>
      <h1>Payment APP With Paging</h1>
      <div className="text-end">
        <div>
          <label>CustomerId</label>
          <input type="number" min="1" onChange={handleCustomerIdChange} />
        </div>
        <div>
          <label>Payment Type</label>
          <select
            value={paymentType}
            onChange={handlePaymentTypeChange}
          >
            {paymentTypes.map((val, index) => (
              <option value={val}>{val}</option>
            ))}
          </select>
        </div>
      </div>
      <DataTable
        columns={columns}
        data={data}
        highlightOnHover
        pagination
        paginationServer
        paginationTotalRows={totalRows}
        paginationPerPage={countPerPage}
        paginationComponentOptions={{
          noRowsPerPage: true,
        }}
        onChangePage={handlePageChange}
      />
    </div>
  );
}

export default PagingComponent;
